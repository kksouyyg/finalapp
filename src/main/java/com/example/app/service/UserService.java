package com.example.app.service;

import com.example.app.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService { /*서비스는 맵퍼를 항상 가져다 쓴다.*/
    private final UserMapper userMapper;
}

/*그리고 나서 컨트롤러랑 연결하자.*/
