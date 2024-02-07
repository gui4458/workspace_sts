package com.test.Test.service;

import com.test.Test.vo.ClassVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<ClassVO> classInfo() {
        return sqlSession.selectList("testMapper.classInfo");
    }
}
