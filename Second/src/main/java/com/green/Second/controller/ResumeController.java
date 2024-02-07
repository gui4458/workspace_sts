package com.green.Second.controller;

import com.green.Second.vo.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    @GetMapping("/name")
    public String name(){
        return "name";
    }

    @GetMapping ("/info")
    public String info(@RequestParam(name = "name", required = false,defaultValue = "입력값 없음") String name
                        , @RequestParam(name = "tel", required = false,defaultValue = "입력값 없음") String tel
                        , Model model){

        model.addAttribute("name", name);
        model.addAttribute("tel", tel);

        return "info";
    }
    @PostMapping ("/check")
    public String check(@RequestParam(name = "name", required = false,defaultValue = "입력값 없음") String name
            , @RequestParam(name = "tel", required = false,defaultValue = "입력값 없음") String tel
            , @RequestParam(name = "academic", required = false) String academic
            , @RequestParam(name = "scool", required = false) String scool
            , @RequestParam(name = "old", required = false) String old
            , @RequestParam(name = "qualification", required = false) String qualification
            , @RequestParam(name = "acquisition", required = false) String acquisition
            , @RequestParam(name = "issuing", required = false) String issuing
            , @RequestParam(name = "companyName", required = false) String companyName
            , @RequestParam(name = "djqan", required = false, defaultValue = "입력값 없음") String djqan
            , @RequestParam(name = "myInfo", required = false) String myInfo
            , @RequestParam(name = "career1", required = false) String career1
            , @RequestParam(name = "career2", required = false) String career2
            , Model model, ResumeVO resumeVO) {

        String allQualification = qualification + acquisition + issuing;
        String sumCareer = career1 + " 년 " + career2 + " 개월";
        String allCareer = companyName + " " + djqan + " " + sumCareer;
        model.addAttribute("djqan",djqan);
        model.addAttribute("name",name);
        model.addAttribute("tel",tel);
        model.addAttribute("academic",academic);
        model.addAttribute("scool",scool);
        model.addAttribute("old",old);
        model.addAttribute("myInfo",myInfo);
        model.addAttribute("allCareer",allCareer);
        model.addAttribute("allQualification",allQualification);

        System.out.println(resumeVO);

        return "check";
    }



}
