package com.example.skhuthon_0th_team10.dto;

import com.example.skhuthon_0th_team10.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResDto {
    private String name;
    private String email;
    private String pictureUrl;


    public static UserResDto of(User user) {
        return UserResDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .pictureUrl(user.getPictureUrl())
                .build();
    }
}
