package com.example.app.service;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import com.example.app.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    /* boardMapper를 가져다 쓴다. */

    //    게시물 삽입
    public void register(BoardDto boardDto){
        /*insert*/

        boardMapper.insert(boardDto);



    }

    //    게시물 삭제
    public void remove(Long boardNumber){
        /*delete*/

        boardMapper.delete(boardNumber);
    }

    //    게시물 수정
    public void modify(BoardDto boardDto){
      /*  update*/

        boardMapper.update(boardDto);

    }

    //    게시물 단건 조회
    public BoardVo find(Long boardNumber){
      /*  select*/

        Optional<BoardVo> opt = boardMapper.select(boardNumber);
return opt.orElseThrow(() -> new IllegalStateException("존재하지 않는 게시물 정보") );

/*옵셔널이 넘버를 가지고 있으면 보이고 없으면 예외를 발생시켜라
* public  Optional<BoardVo>를 BoardVo로 변경
* */
        /*list html의  <!-- 게시물 샘플 --> 쪽의 1개 네모 박스인데 이거 여러개 나올려면 위에 게시물 단건 조회를 몇번 돌려보자.*/


    }

    //    게시물 리스트 조회
    public List<BoardVo> findList(){
/*        selectList*/

        List<BoardVo> boardList = boardMapper.selectList();
        return boardList;


    }



}
