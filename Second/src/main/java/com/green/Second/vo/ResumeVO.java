package com.green.Second.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ResumeVO {


    private String name;
    private String tel;
    private String academic; //학력
    private String scool;   //학교명
    private String old; //지원구분
    private String qualification;//자격증명
    private String acquisition;//취득일자
    private String issuing; //발행기관
    private String companyName;//회사명
    private String djqan; // 담당업무
    private String career1; // 경력
    private String career2; // 경력
    private String myInfo; // 자기소개

}
