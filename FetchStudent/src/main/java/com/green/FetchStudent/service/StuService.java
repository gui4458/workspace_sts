package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {
    List<StuVO> selectStu(StuVO stuVO);

    List<ClassVO> selectClassList();

    StuVO stuDetail(StuVO stuVO);

    ScoreVO scoreInfo(int stuNum);

    void insertScore(ScoreVO scoreVO);
}
