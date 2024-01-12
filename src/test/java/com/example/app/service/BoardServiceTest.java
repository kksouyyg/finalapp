package com.example.app.service;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

 @SpringBootTest
class BoardServiceTest {
     @Autowired
     BoardService boardService;


    @Test
    void register() {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardTitle("test 타이틀");
        boardDto.setBoardContent(" test content");
        boardDto.setUserNumber(8L);
        boardService.register(boardDto);
        /*TBl_user 테이블의 있는 번호를 넣어 확인해야 한다. 8L*/

    }

    @Test
    void remove() {
        boardService.remove(10L);
        /*boardNumber을 지워야하니까 Tbl_board 테이블 보는거다.*/
    }

    @Test
    void modify() {
        BoardDto boardDto =  new BoardDto();
        boardDto.setBoardTitle("타이틀 수정");
        boardDto.setBoardContent("content 수정한다.");
        boardDto.setBoardNumber(2L);  /* TBL_보드 테이블의 넘버 있는거 확인하고 수정하자. */

        boardService.modify(boardDto);  /* 수정된 정보를 가진  dto 넘긴다 */
    }

    @Test
    void find() { /* select 단건조회 */
        BoardVo boardVo = boardService.find(2L); /*보드 테이블에 있는 번호 넣기*/
        System.out.println("boardVo = " + boardVo);
    }


    @Test
    void findList() { /* 보드 테이블의 토탈 리스트 */
        List<BoardVo> list = boardService.findList();
        System.out.println("list = " + list);

    }





}