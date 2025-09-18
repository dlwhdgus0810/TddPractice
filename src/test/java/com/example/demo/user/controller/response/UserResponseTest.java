package com.example.demo.user.controller.response;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseTest {
    
    @Test
    void User으로_응답을_생성할_수_있다() {
        //given
        User user = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(UserStatus.ACTIVE)
                .certificationCode(UUID.randomUUID().toString())
                .build();

        //when
        UserResponse result = UserResponse.from(user);

        //then
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(result.getNickname()).isEqualTo("hyun");
        
    }

}