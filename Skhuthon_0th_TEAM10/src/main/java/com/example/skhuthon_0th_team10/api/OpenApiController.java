package com.example.skhuthon_0th_team10.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Map;

@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
public class OpenApiController {

    private final NaverBlogSearchApi naver;
    private final Gson gson;

    @GetMapping("naver/blog")
    public ResponseEntity<Map<String, Object>> getPlace(@RequestParam("query") String query) {
        String response = naver.search(query);
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> jsonMap = gson.fromJson(response, type);
        return ResponseEntity.ok(jsonMap);
    }
}
