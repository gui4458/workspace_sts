package com.green.First.controller;

import com.green.First.vo.RegVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("reg_member")
    public String reg_member(){
        return "reg_member";
    }

//    커맨드 객체 : 컨트롤러의 메소드의 매개변수 중에서
//    넘어오는 데이터를 받기 위해 선언한 객체
//    커맨드 객체는 model을 사용하지 않아도 자동으로
//    html로 데이터가 전달 됨.
//    커맨드 객체가 자동으로 넘어갈 때 넘어가는 데이터의 이름은
//    클래스명에서 앞글자만 소문자로 바꾼 이름으로 전달 됨.
    @PostMapping("reg_member_detail")
    public String reg_member_detail(RegVO regVO){
        System.out.println(regVO);
        return "reg_member_detail";
    }

    @PostMapping("member_info")
    public String member_info(RegVO regVO){
        System.out.println(regVO);
//        String tel="";
//        String lesson="";
//        for (String e : regVO.getTel()) {
//            tel +=  e;
//        }
//        for (String e : regVO.getLesson()) {
//            lesson +=  e;
//        }
        return "member_info";

    }


}
