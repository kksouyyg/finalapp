package com.example.app.mapper;

import com.example.app.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;
import java.util.OptionalLong;

@Mapper
public interface UserMapper {

    // 로그인시 회원 정보 삽입
    void insert(UserDto userDto);

    // 로그인시 회원 정보 조회
    Optional<Long> selectUserNumber(@Param("userId") String userId,
                                    @Param("userPassword") String userPassword);
    // null이 나오면 안되서 Optional<Long>
    // 아이디 페스워드를 넘길거다. @Parm쓰자.
    // 서비스쪽이랑 연결전에 ctrl+t    test하자.
}
