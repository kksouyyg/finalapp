package com.example.app.service;

import com.example.app.domain.dto.UserDto;
import com.example.app.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
@Mock
    UserMapper userMapper;


@InjectMocks
UserService userService;
UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserNumber(1L);
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");

    }

    @Test
    void register() {
        // given
        doNothing().when(userMapper).insert(any());
                // Mockito.doNothing()
        // when
        userService.register(userDto);
        // then
verify(userMapper, times(1)).insert(any());

    }

    @Test
    void findUserNumber() {
        // given
         doReturn(Optional.of(1L)).when(userMapper).selectUserNumber(any(), any());
         // 맵퍼가 옵션널이라 넣아주고 즉, 1번 반환해줘
        // when
        Long foundnumber = userService.findUserNumber("aaa", "1234");

        // then
        assertThat(foundnumber)
                .isNotNull()
                .isEqualTo(1L);
        // Assertions.assertThat()
    }

// 오류상황 보기
    @Test
    void findUserNumberException() {
        // given
        doReturn(Optional.empty()).when(userMapper).selectUserNumber(any(), any());
        // 비어 있는걸 받아 일부러 에러가 나게 함.
        // when


        // then
       assertThatThrownBy(() -> userService.findUserNumber("aaa", "1234"))
               .hasMessageContaining("정보 없음");
    }


}