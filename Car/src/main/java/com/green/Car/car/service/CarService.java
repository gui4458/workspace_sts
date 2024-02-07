package com.green.Car.car.service;

import com.green.Car.car.vo.CarVO;

import java.util.List;

public interface CarService {
    void insertCar(CarVO carVO);

    List<CarVO> selectCarList();

    void insertSales(CarVO carVO);
}
