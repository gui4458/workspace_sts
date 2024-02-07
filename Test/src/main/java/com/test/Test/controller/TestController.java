package com.test.Test.controller;

import com.test.Test.service.TestServiceImpl;
import com.test.Test.vo.ClassVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("test")
@Controller
public class TestController {
    @Resource
    private TestServiceImpl testService;

    @GetMapping("/")
    public String classInfo(Model model){
        List<ClassVO> classInfo = testService.classInfo();
        model.addAttribute("classInfo",classInfo);
        return "stu";
    }
}
