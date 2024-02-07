package com.green.Board2.controller;

import com.green.Board2.service.MemberServiceImpl;
import com.green.Board2.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;

    //로그인 페이지로 이동
    @GetMapping ("/loginForm")
    public String loginForm(){
        return "login";
    }
    //회원가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm(){
        return "/join";
    }
    //가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        memberService.joinMember(memberVO);
        return "redirect:/member/loginForm";
    }
    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);
//        로그인 정보가 조회가 됐으면
        if (loginInfo != null){

//            세션에 로그인 정보를 저장
            session.setAttribute("loginInfo",loginInfo);
        }
        return "login_result";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/board/list";
    }
}
