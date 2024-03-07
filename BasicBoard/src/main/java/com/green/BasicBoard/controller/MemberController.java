package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BoardService;
import com.green.BasicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Resource(name="boardService")
    private BoardService boardService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //회원가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm(){

        return "join";
    }
    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){

        memberVO.setMemberPw(encoder.encode(memberVO.getMemberPw()));

        boardService.join(memberVO);
        return "redirect:/loginForm";
    }
    //로그인페이지이동
    @GetMapping("/loginForm")
    public String loginForm(){

        return "login";
    }



}
