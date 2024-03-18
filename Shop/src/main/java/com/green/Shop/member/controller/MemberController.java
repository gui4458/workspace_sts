package com.green.Shop.member.controller;

import com.green.Shop.member.service.MemberServiceImpl;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name="memberService")
    private MemberServiceImpl memberService;


    @PostMapping("/insertMember")
    public String insertMember(MemberVO memberVO){

        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",", ""));
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",","-"));

        memberService.insertMember(memberVO);

        return "redirect:/item/list";
    }
//    로그인페이지로이동
    @GetMapping("/login")
    public String login(@RequestParam(name = "errorMsg",required = false,defaultValue = "success") String errorMsg,Model model){
        model.addAttribute("errorMsg",errorMsg);
        return "content/member/login";
    }

    //    로그인 결과 화면
    @GetMapping("/loginResult")
    public String loginResult(){

        return "content/member/login_result";

    }

//   로그인
//    @PostMapping("/goLogin")
//    public String goLogin(MemberVO memberVO, HttpSession session, Model model){
//
//        MemberVO loginInfo =  memberService.login(memberVO.getMemberId());
////        로그인정보가맞으면
//        if (loginInfo != null){
//            session.setAttribute("loginInfo",loginInfo);
//        }
//        return "content/member/login_result";
//    }
//로그아웃
//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute("loginInfo");
//        return "redirect:/item/list";
//    }

//비동기 로그인
    @ResponseBody
    @PostMapping("/loginFetch")
    public String loginFetch(MemberVO memberVO,HttpSession session){
        MemberVO loginInfo =  memberService.login(memberVO.getMemberId());
//        로그인정보가맞으면
        if (loginInfo != null){
            session.setAttribute("loginInfo",loginInfo);
        }
        return loginInfo == null ? "" : loginInfo.getMemberId();
    }


}
