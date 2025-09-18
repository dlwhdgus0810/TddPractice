package com.example.demo.post.service;

import com.example.demo.mock.FakePostRepository;
import com.example.demo.mock.FakeUserRepository;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.PostCreate;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.demo.user.domain.UserStatus.ACTIVE;
import static com.example.demo.user.domain.UserStatus.PENDING;
import static org.assertj.core.api.Assertions.assertThat;

public class PostServiceTest {

    private PostService postService;

    @BeforeEach
    void init() {
        User writer = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
                .lastLoginAt(1678530673958L)
                .build();

        User user2 = User.builder()
                .id(2L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(PENDING)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
                .lastLoginAt(1678530673958L)
                .build();

        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        fakeUserRepository.save(writer);
        fakeUserRepository.save(user2);

        Post post = Post.builder()
                .content("hello world")
                .createdAt(1678530673958L)
                .modifiedAt(0L)
                .writer(writer)
                .build();

        FakePostRepository fakePostRepository = new FakePostRepository();
        fakePostRepository.save(post);

        this.postService = PostService.builder()
                .userRepository(fakeUserRepository)
                .postRepository(fakePostRepository)
                .clockHolder(new TestClockHolder(1678530673958L))
                .build();
    }

    @Test
    void PostCreate를_이용하여_게시물을_생성할_수_있다() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1L)
                .content("hello world")
                .build();

        //when
        Post result = postService.createPost(postCreate);

        //then
        assertThat(result.id()).isNotNull();
        assertThat(result.content()).isEqualTo("hello world");
        assertThat(result.createdAt()).isEqualTo(1678530673958L);
    }

    @Test
    void PostUpdate를_이용하여_게시물을_수정할_수_있다() {
        //given
        PostUpdate postUpdate = PostUpdate.builder()
                .content("world hello!")
                .build();

        //when
        Post post = postService.updatePost(1L, postUpdate);

        //then
        assertThat(post.id()).isNotNull();
        assertThat(post.content()).isEqualTo("world hello!");
        assertThat(post.createdAt()).isEqualTo(1678530673958L);
    }
}
