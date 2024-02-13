package com.green.Car.sales.service;

import com.green.Car.sales.vo.SalesVO;

import java.util.List;

public interface SalesService {
    void insertSales(SalesVO salesVO);

    List<SalesVO> salesList();

}
