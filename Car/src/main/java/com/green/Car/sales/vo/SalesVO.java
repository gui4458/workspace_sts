package com.green.Car.sales.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesVO {
    private int saleNum;
    private String buyerName;
    private String buyerTel;
    private String color;
    private String saleDate;
    private int modelNum;
}
