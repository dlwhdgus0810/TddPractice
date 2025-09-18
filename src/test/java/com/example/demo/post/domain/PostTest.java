package com.example.demo.post.domain;

import com.example.demo.mock.TestClockHolder;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @Test
    void PostCreate으로_게시물을_만들_수_있다() {
        //given
        String content = "hello world";
        PostCreate postCreate = PostCreate.builder()
                .writerId(1L)
                .content(content)
                .build();

        User writer = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(UserStatus.ACTIVE)
                .certificationCode(UUID.randomUUID().toString())
                .build();

        //when
        Post post = Post.from(writer, postCreate, new TestClockHolder(1678530673958L));

        //then
        assertThat(post.content()).isEqualTo(content);
        assertThat(post.writer().getEmail()).isEqualTo("hyunlee.289@gmail.com");
        assertThat(post.writer().getAddress()).isEqualTo("deokso");
        assertThat(post.writer().getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(post.writer()).isEqualTo(writer);
    }
}
