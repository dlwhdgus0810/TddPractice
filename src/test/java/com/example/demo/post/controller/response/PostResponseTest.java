package com.example.demo.post.controller.response;

import com.example.demo.post.domain.Post;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PostResponseTest {

    @Test
    void Post로_응답을_생성할_수_있다() {
        //given
        User writer = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(UserStatus.ACTIVE)
                .certificationCode(UUID.randomUUID().toString())
                .build();

        Post post = Post.builder()
                .content("hello world")
                .writer(writer)
                .build();

        //when
        PostResponse postResponse = PostResponse.from(post);
        UserResponse userResponse = postResponse.getWriter();

        //then
        assertThat(postResponse.getContent()).isEqualTo(post.content());
        assertThat(userResponse.getEmail()).isEqualTo("hyunlee.289@gmail.com");
        assertThat(userResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(userResponse.getNickname()).isEqualTo("hyun");
    }
}
