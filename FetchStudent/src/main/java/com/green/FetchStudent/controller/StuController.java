package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.DetailVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/stu")
public class StuController {

    @Resource(name="stuService")
    private StuServiceImpl stuService;

    @RequestMapping("/list")
    public String selectStu(StuVO stuVO, Model model){
        List<ClassVO> classList = stuService.selectClassList();
        model.addAttribute("classList",classList);

        List<StuVO> list = stuService.selectStu(stuVO);
        model.addAttribute("stuList",list);

        return "stu_manage";

    }

    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        List<StuVO> stuList = stuService.selectStu(stuVO);
        return stuList;
    }
    @ResponseBody
    @PostMapping("/goStuDetail")
    public DetailVO goStuDetail(StuVO stuVO){
//        클릭한 학생의 상세 정보 조회
//        클릭한 학생의 점수 정보 조회
        StuVO stu = stuService.stuDetail(stuVO);

        ScoreVO score = stuService.scoreInfo(stuVO.getStuNum());
        DetailVO detailVO=new DetailVO();
        detailVO.setStuVO(stu);
        detailVO.setScoreVO(score);


        return detailVO;
//        조회한 데이터를 가지고 자바스크립트로 이동
//        StuVO stu = stuService.stuDetail(stuVO);
//        return stu;
    }

    @ResponseBody
    @PostMapping("/insertScore")
    public void insertScore(ScoreVO scoreVO){
        stuService.insertScore(scoreVO);
    }

}
