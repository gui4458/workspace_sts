package com.green.Car.sales.service;

import com.green.Car.sales.vo.SalesVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("salesService")
public class SalesServiceImpl implements SalesService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertSales(SalesVO salesVO) {
        sqlSession.insert("salesMapper.insertSales",salesVO);
    }

    @Override
    public List<SalesVO> salesList() {
        return sqlSession.selectList("salesMapper.salesList");
    }

}
