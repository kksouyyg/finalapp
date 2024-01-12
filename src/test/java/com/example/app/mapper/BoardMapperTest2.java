package com.example.app.mapper;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest2 {
@Autowired
    BoardMapper boardMapper;

    @Test
    void insert() {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardTitle("test title");
        boardDto.setBoardContent("test content");
        boardDto.setUserNumber(8L);

        boardMapper.insert(boardDto);
    }

    @Test
    void delete() {
        boardMapper.delete(9L);
    }

    @Test
    void update() {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardTitle("test update");
        boardDto.setBoardContent("update content");
  boardDto.setBoardNumber(10L);

        boardMapper.update(boardDto);
    }

    @Test
    void select() {
        Optional<BoardVo> found = boardMapper.select(10L);
        BoardVo boardVo = found.orElse(null);
        /*조회가 잘 되었으면 vo를 꺼내오고 없으면 null를 반환해라.*/

        System.out.println("boardVo = " + boardVo);
        /*select는 찍어서 확인한다.
        * boardVo는 userId도 잘 나오는지 확인해 보자.*/

    }

    /*게시물 리스트 조회 */
    @Test
    void selectList() {

        List<BoardVo> boardList = boardMapper.selectList();
        System.out.println("boardList = " + boardList);

    }
}