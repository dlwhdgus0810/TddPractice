package com.example.demo.post.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record PostUpdate(String content) {

    @Builder
    public PostUpdate(
            @JsonProperty("content") String content) {
        this.content = content;
    }
}
