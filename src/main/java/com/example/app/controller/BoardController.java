package com.example.app.controller;

import com.example.app.domain.dto.BoardDto;
import com.example.app.domain.vo.BoardVo;
import com.example.app.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 단순 진입용
    @GetMapping("/list")
    public String boardList(Model model){

        List<BoardVo> boardList = boardService.findList();
        /*리스트 페이지에서 글 쓴 리스트를 전부 가지고 보여줘야 한다.
        보드서비스를 주입받기 때문에 서비스의 파인드리스트를 실행시키면 목록 다나온다.*/

        model.addAttribute("boardList", boardList); /* 화면에서 받기*/

        return "board/list"; /*화면에다 출력하기*/
    }





    // 단순 진입용
    @GetMapping("/view")
    public String boardView(){
        return "board/view";
    }

    // 단순 진입용
    @GetMapping("/write")
    public String boardWrite(HttpSession session){
        Long userNumber = (Long) session.getAttribute("userNumber");
        /* 지금 로그인 한 사람이 로그인을 했나 안했나 검사하자.  세션에 로그인 할때 넣은 유저 넘버를 내놔라하고 알려준다. */

        if(userNumber == null){
            return "user/login";
        }
        /*로그인 했으면 정보가 나오고 null이면 로그인 페이지로 이동해  즉, list html 파일의 새 개시글 작성 버튼을 누르면 로그인 페이지로 이동한다
        *
        * */

        return "board/write";

    }


    @PostMapping("/write")
    public RedirectView boardWrite(BoardDto boardDto, HttpSession session){   /*String*/
        /*화면에서 전달하는 데이터를 받아야 한다. writer html 문서의 폼 a c t i o n 부분이다.*/

        Long userNumber = (Long) session.getAttribute("userNumber");
        boardDto.setUserNumber(userNumber);  /*세션에서 뽑고 보드DTo에 유저 넘버를 넘겨주고*/


        System.out.println("boardDto :" + boardDto);

        boardService.register(boardDto);
        /*보드 서비스한테 받은 인서트를 보드 D t o 에 전달해준다. 근데 저장할때 필요한 값을 기억해야 한다.
        * 보드 맵퍼의 인설트를 보면 구망이 4개인데 html 문서는 구멍이 2개다.
        * 즉, 유저 넘버가 화면에서 받을 수가 없다.
        * 로그인을 하면 세션에 유저 넘버가 있다.
        * 그래서 세션에서 뽑아야한다. 먼저 세센에서 뽑야야 한다.
        * */

        return new RedirectView("board/list");

        /*해보면 write.html 문서 화면에 데이터가 들어간게 안보인다.
        * 위에 /l i s t를 보면 M o d e l를 써 놨다. 근데 복사해서 쓰기 싫다.
        * 그래서 String 대신 Redirect V i e w 를 쓰자.
        * */

    }

}
