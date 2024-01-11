package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// 매퍼 인터페이스로 등록을 하기위한 어노테이션
@Mapper
public interface TimeMapper {  // ctrl+shift+t  이걸로 꼭 test를 먼저 진행한다.

    String selectTime();

    @Select("SELECT SYSDATE FROM DUAL")
    String selectTime2();
}








