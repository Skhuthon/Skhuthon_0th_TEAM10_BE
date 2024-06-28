package com.example.skhuthon_0th_team10.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AwsS3Service {
    private static final Logger log = LoggerFactory.getLogger(AwsS3Service.class);

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirname) throws IOException {
//        File uploadFile = convert(multipartFile)
//                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));
//        return upload(uploadFile, dirname, multipartFile.getOriginalFilename());
        if (multipartFile == null) {
            return "https://s3.ap-northeast-2.amazonaws.com/tasty-inventory-be-image/menu/image/4ae07266-577b-41bd-aa07-a8644c853914.jpeg2024-06-18T03%3A57%3A09.656107790";
        }
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try(InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket+"/"+ dirname + "/image", fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3.getUrl(bucket+"/"+ dirname + "/image", fileName).toString();
        } catch(IOException e) {
            throw new IOException("파일 입출력 오류");
        } catch (AmazonServiceException e) {
            log.error("AmazonServiceException: Error Message: " + e.getMessage());
            log.error("HTTP Status Code: " + e.getStatusCode());
            log.error("AWS Error Code: " + e.getErrorCode());
            log.error("Error Type: " + e.getErrorType());
            log.error("Request ID: " + e.getRequestId());
            throw new RuntimeException("Amazon 서비스 오류");
        } catch (SdkClientException e) {
            throw new RuntimeException("SDK 오류");
        }
    }

    // 파일명 (중복 방지)
    private String createFileName(String fileName) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return UUID.randomUUID().toString().concat(getFileExtension(fileName)) + localDateTime;
    }

    // 파일 유효성 검사
    private String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw new IllegalArgumentException("파일 없음");
        }
        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".JPG");
        fileValidate.add(".jpeg");
        fileValidate.add(".JPEG");
        fileValidate.add(".png");
        fileValidate.add(".PNG");
        fileValidate.add(".webp");
        fileValidate.add(".WebP");
        fileValidate.add(".heif");
        fileValidate.add(".HEIF");
        fileValidate.add(".heic");
        fileValidate.add(".HEIC");
        fileValidate.add(".svg");
        fileValidate.add(".SVG");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new IllegalArgumentException("잘못된 파일 이름");
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String upload(File uploadFile, String dirname, String originalName) {
        String fileName = dirname + "/" + UUID.randomUUID() + originalName;
        String uploadImageUrl = putS3(uploadFile, fileName);

        removeFile(uploadFile);
        return uploadImageUrl;
    }

    private Optional<File> convert(MultipartFile file) throws IOException { // 이미지 파일화
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private String putS3(File uploadFile, String fileName) {    // 파일 업로드
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeFile(File targetFile) {  // 파일 삭제
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 않았습니다.");
        }
    }
}
