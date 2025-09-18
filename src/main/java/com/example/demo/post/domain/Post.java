package com.example.demo.post.domain;

import com.example.demo.user.domain.User;
import lombok.Builder;

import java.time.Clock;

public record Post(Long id, String content, Long createdAt, Long modifiedAt, User writer) {
    @Builder
    public Post {}

    public static Post from(User writer, PostCreate postCreate) {
        return Post.builder()
                .writer(writer)
                .content(postCreate.getContent())
                .createdAt(Clock.systemUTC().millis())
                .build();
    }

    public Post update(PostUpdate postUpdate) {
        return Post.builder()
                .id(id)
                .content(postUpdate.getContent())
                .createdAt(createdAt)
                .modifiedAt(Clock.systemUTC().millis())
                .writer(writer)
                .build();
    }
}
