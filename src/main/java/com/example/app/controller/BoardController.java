package com.example.app.controller;

import com.example.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 단순 진입용
    @GetMapping("/list")
    public String boardList(){
        return "board/list";
    }


    // 단순 진입용
    @GetMapping("/view")
    public String boardView(){
        return "board/view";
    }

    // 단순 진입용
    @GetMapping("/write")
    public String boardWrite(){
        return "board/write";
    }

}
