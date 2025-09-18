package com.example.demo.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record UserCreate(String email, String nickname, String address) {

    @Builder
    public UserCreate(
            @JsonProperty("email") String email,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("address") String address) {
        this.email = email;
        this.nickname = nickname;
        this.address = address;
    }
}
