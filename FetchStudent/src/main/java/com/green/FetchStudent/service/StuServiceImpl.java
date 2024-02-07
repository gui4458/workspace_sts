package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("stuService")
public class StuServiceImpl implements StuService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<StuVO> selectStu(StuVO stuVO) {
        return sqlSession.selectList("stuMapper.selectStu", stuVO);

    }

    @Override
    public List<ClassVO> selectClassList() {
        return sqlSession.selectList("stuMapper.selectClassList");
    }

    @Override
    public StuVO stuDetail(StuVO stuVO) {
        return sqlSession.selectOne("stuMapper.stuDetail",stuVO);
    }

    @Override
    public ScoreVO scoreInfo(int stuNum) {
        return sqlSession.selectOne("stuMapper.scoreInfo",stuNum);
    }

    @Override
    public void insertScore(ScoreVO scoreVO) {
        sqlSession.insert("stuMapper.insertScore",scoreVO);
    }


}
