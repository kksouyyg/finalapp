package com.example.app.mapper;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.dto.UserDto;


import com.example.app.domain.vo.BoardVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class BoardMapperTest {
@Autowired
    BoardMapper boardMapper;

@Autowired
        UserMapper userMapper;
BoardDto boardDto;
UserDto userDto;


    @BeforeEach
    void setUp() {
        userDto = new UserDto(); /*실제적인 DB에 없는 데이터 넣기*/
        userDto.setUserId("test");
        userDto.setUserPassword("test");
        userDto.setUserGender("N");
        userDto.setUserEmail("test@naver.com");
        userDto.setUserAddress("노원구");

        userMapper.insert(userDto);
/*Dto에 유저 디비를 넣으면 유저 넘버가 1개 생긴다.*/

        boardDto = new BoardDto();
        boardDto.setUserNumber(userDto.getUserNumber());
        boardDto.setBoardTitle("test title");
        boardDto.setBoardContent("test content");

    }


    @Test
    void delete() {
        // given
         /*디비에 다녀와야한다.*/
        boardMapper.insert(boardDto);

        // when
        boardMapper.delete(boardDto.getBoardNumber());
        // then
        BoardVo boardVo = boardMapper.select(boardDto.getBoardNumber()).orElse(null);
        assertThat(boardVo).isNull();
/*삭제되면 널이 들어가 있으니 널이니라고 검증한다.*/
      /*Assertions.assertThat()*/
    }

    @Test
    void update() {
        // given
        boardMapper.insert(boardDto);
boardDto.setBoardTitle("title update");
boardDto.setBoardContent("content update");
        // when
        boardMapper.update(boardDto);
        // then
        BoardVo boardVo = boardMapper.select(boardDto.getBoardNumber()).orElse(null);/*넣은거 찾기*/

        assertThat(boardVo.getBoardTitle()).isEqualTo("title update");
    }

    @Test
    void select() {
        // given
         boardMapper.insert(boardDto);
         boardDto.setBoardNumber(1L);
        // when
        Optional<BoardVo> select = boardMapper.select(boardDto.getBoardNumber());
        // then
assertThat(select).isEmpty().isNotNull();


    }

    @Test
    void selectList() {
    }
}