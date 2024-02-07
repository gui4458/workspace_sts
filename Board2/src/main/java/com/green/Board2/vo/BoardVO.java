package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {
    private int boardNum;
    private String title;
    private String content;
    private String writer;
    private String createDate;
    private int readCnt;
    private String searchOption;
    private String searchText;
    private int num1;
    private int num2;


}
