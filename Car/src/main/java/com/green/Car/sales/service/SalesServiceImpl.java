package com.green.Car.sales.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("salesService")
public class SalesServiceImpl implements SalesService{
    @Autowired
    private SqlSessionTemplate sqlSession;
}
