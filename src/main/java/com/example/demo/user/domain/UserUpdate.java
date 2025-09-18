package com.example.demo.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record UserUpdate(String nickname, String address) {

    @Builder
    public UserUpdate(
            @JsonProperty("nickname") String nickname,
            @JsonProperty("address") String address) {
        this.nickname = nickname;
        this.address = address;
    }
}
