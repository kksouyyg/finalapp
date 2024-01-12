package com.example.app.service;

import com.example.app.domain.dto.UserDto;
import com.example.app.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService { /*서비스는 맵퍼를 항상 가져다 쓴다.*/
    private final UserMapper userMapper;

    //    회원 정보 삽입
    public void register(UserDto userDto){
        userMapper.insert(userDto);
    }

    //    회원 번호 조회
    public Long findUserNumber(String userId, String userPassword){
        return userMapper.selectUserNumber(userId, userPassword)
                .orElseThrow(() -> new IllegalStateException("일치하는 회원 정보 없음"));
    }

}

/*그리고 나서 컨트롤러랑 연결하자.*/
