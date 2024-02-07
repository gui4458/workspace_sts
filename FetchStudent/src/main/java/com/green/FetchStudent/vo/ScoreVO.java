package com.green.FetchStudent.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScoreVO {
    private int scoreNum;
    private int stuNum;
    private int korScore;
    private int engScore;
    private int mathScore;
}
