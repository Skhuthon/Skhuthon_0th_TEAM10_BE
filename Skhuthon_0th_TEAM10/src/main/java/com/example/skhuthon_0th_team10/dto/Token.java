package com.example.skhuthon_0th_team10.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Token {
    @SerializedName("access_token")
    private String accessToken;
}
