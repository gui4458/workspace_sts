package com.green.Car.car.service;

import com.green.Car.car.vo.CarVO;
import com.green.Car.sales.vo.SalesVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public void insertCar(CarVO carVO) {
        sqlSession.insert("carMapper.insertCar",carVO);
    }

    @Override
    public List<CarVO> selectCarList() {
        return sqlSession.selectList("carMapper.selectCarList");
    }


}
