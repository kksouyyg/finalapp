package com.example.app.controller;

import com.example.app.domain.dto.UserDto;
import com.example.app.mapper.UserMapper;
import com.example.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /* 단순 진입용 */
    @GetMapping("/join")
    public String join(){

        return "user/join";
    }

    @PostMapping("/join")
    public RedirectView join(UserDto userDto){
        userService.register(userDto);/*서비스의 register 전달하면 가입처리 끝*/
        /* 가입 완료되면 로그인 페이지로 보낼거다.
        new RedirectView("/uer/login"); 제요청된다. 아래    @GetMapping("/login")한테 다시 요청이 들어가
          다시 로그인 페이지로 이동된다.*/

        return new RedirectView("/user/login");
    }

    // 단순 진입용
    @GetMapping("/login")
    public String login(){

        return "user/login";
    }

    //login html의 폼 post와 연결
    @PostMapping("/login")
    public RedirectView login(String userId, String userPassword,
                              HttpSession session){


       /* Long userNumber = userService.findUserNumber(userId, userPassword);*/
   /*여기에 아이디랑 패스워드 넣어야 한다. 그럼 받아야 한다. String userId, String userPassword 넣어준다.
        String userId, String userPassword는 login의 name와 동일한 이름이다.
        */


        Long userNumber = null;
        try {
            userNumber = userService.findUserNumber(userId, userPassword);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new RedirectView("/user/login") ;
            /*로그인 페이지로 제요청 보내 */
        } catch (Exception e){
e.printStackTrace();
        }

/*ctrl+alt+t     유저서비스의 예외가 있어 화면에 오류가 난다 이걸 방지하기 위해 여기서 사용한다..*/




        /*로그인을 했다라고 하면 세션에 저장해야 한다. 나중에 요청이 들어오면 로그인을 했는지 않했는지 알수 있다. 즉 넘버를 세션에 넣어야 한다.
         *   HttpSession session와     session.setAttribute("userNumber", userNumber); 를 추가한다.
         *  */

//        input 태그 안에 name과 같아야한다.

//        로그인 했다는 것을 기억하기 위해서
//        Session에 저장해야 한다.
//                고로 pk를 Session에 저장해 놓으면 된다.

        session.setAttribute("userNumber", userNumber);
        /*void 를 RedirectView 바꿔주고 아래 return 쓰자.*/

        return new RedirectView("/board/list");
    }





/*logout 하기*/
    @GetMapping("/logout")
public RedirectView logout(HttpSession session){
        session.invalidate();
        return new RedirectView("/user/login");

    }
    // 세션에 넣은거 지운다. session.invalidate(); 초기화시켜 저장된 값을 모두 날린다. 세션은 서버에 저장된다. 로그아웃은 디비에 갈 필요가 없다.
    //void 를 RedirectView로 바꿔주고



}












