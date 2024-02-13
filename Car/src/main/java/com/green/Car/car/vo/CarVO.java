package com.green.Car.car.vo;

import com.green.Car.sales.vo.SalesVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarVO {
    private int modelNum;
    private String modelName;
    private int price;
    private String maker;
}
