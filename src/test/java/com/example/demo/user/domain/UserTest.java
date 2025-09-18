package com.example.demo.user.domain;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import org.junit.jupiter.api.Test;

import static com.example.demo.user.domain.UserStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    void User는_UserCreate_객체로_생성할_수_있다() {
        //given
        UserCreate userCreate = UserCreate.builder()
                .email("hyunlee.289@gmail.com")
                .nickname("jonghyun")
                .address("deokso")
                .build();

        //when
        User user = User.from(userCreate, new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"));

        //then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo(userCreate.email());
        assertThat(user.getNickname()).isEqualTo(userCreate.nickname());
        assertThat(user.getAddress()).isEqualTo(userCreate.address());
        assertThat(user.getStatus()).isEqualTo(PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
    }

    @Test
    void UserUpdate_객체로_데이터를_업데이트_할_수_있다() {
        //given
        User user = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("jonghyun")
                .address("seoul")
                .build();

        //when
        user = user.update(userUpdate);

        //then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("hyunlee.289@gmail.com");
        assertThat(user.getNickname()).isEqualTo("jonghyun");
        assertThat(user.getAddress()).isEqualTo("seoul");
        assertThat(user.getStatus()).isEqualTo(ACTIVE);
        assertThat(user.getLastLoginAt()).isEqualTo(100L);
        assertThat(user.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
    }

    @Test
    void 로그인을_할_수_있고_로그인_시_마지막_로그인_시간이_변경된다() {
        //given
        User user = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(ACTIVE)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .build();
        //when
        user = user.login(new TestClockHolder(1678530673958L));

        //then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("hyunlee.289@gmail.com");
        assertThat(user.getNickname()).isEqualTo("hyun");
        assertThat(user.getAddress()).isEqualTo("deokso");
        assertThat(user.getStatus()).isEqualTo(ACTIVE);
        assertThat(user.getLastLoginAt()).isEqualTo(1678530673958L);
        assertThat(user.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
    }

    @Test
    void 유효한_인증_코드로_계정을_활성화_할_수_있다() {
        //given
        User user = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(PENDING)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .build();

        //when
        user = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

        //then
        assertThat(user.getStatus()).isEqualTo(ACTIVE);
    }

    @Test
    void 잘못된_인증_코드로_계정을_활성화_하려하면_에러를_던진다() {
        //given
        User user = User.builder()
                .id(1L)
                .email("hyunlee.289@gmail.com")
                .nickname("hyun")
                .address("deokso")
                .status(PENDING)
                .lastLoginAt(100L)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .build();

        //when
        //then
        assertThatThrownBy(() -> user.certificate("wrongcertificationcode"))
            .isInstanceOf(CertificationCodeNotMatchedException.class);
    }


}