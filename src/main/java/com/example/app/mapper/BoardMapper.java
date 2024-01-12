package com.example.app.mapper;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    //    게시물 삽입
    void insert(BoardDto boardDto);

    //    게시물 삭제
    void delete(Long boardNumber);

    //    게시물 수정
    void update(BoardDto boardDto);

    //    게시물 단건 조회
    Optional<BoardVo> select(Long boardNumber);

    //    게시물 리스트 조회
    List<BoardVo> selectList();
}



