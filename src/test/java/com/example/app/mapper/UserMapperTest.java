package com.example.app.mapper;

import com.example.app.domain.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserMapperTest {
    @Autowired
    UserMapper userMapper;
 // userMapper 주입받아 씀.
    UserDto userDto;

    @BeforeEach
    void setUp() {  //필드로 userDto 넣고 여기서 초기화 한다.
        userDto = new UserDto();
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");
        userDto.setUserAddress("노원구");
        userDto.setUserEmail("aaa@naver.com");
        userDto.setUserGender("M");

    }


    // 로그인시 회원 정보 조회
    @Test
    void selectUserNumber() {
        // given
         userMapper.insert(userDto);
        // when
        Long foundNumber = userMapper.selectUserNumber("aaa", "1234")
                .orElse(null);
        //  Optional<Long>이라.
        // then
       Assertions.assertThat(foundNumber)
        .isNotNull()
               .isEqualTo(userDto.getUserNumber());
    }
}