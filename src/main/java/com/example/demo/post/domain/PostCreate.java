package com.example.demo.post.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record PostCreate(long writerId, String content) {

    @Builder
    public PostCreate(
            @JsonProperty("writerId") long writerId,
            @JsonProperty("content") String content) {
        this.writerId = writerId;
        this.content = content;
    }
}
