package com.green.BasicBoard.vo;

//import lombok.AllArgsConstructor;
import lombok.*;
//import lombok.Builder;

// 생성자를 구현하는 어노테이션
//@NoArgsConstructor //매개변수가 없는 생성자
//@AllArgsConstructor // 매개변수로 모든 변수를 받는 생성자
//@RequiredArgsConstructor

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberRoll;
}

//기본 생성자, setter,getter, toString


class BuilderTest{
    public void test(){
//        id를 java라는 초기값으로 갖는 객체 생성
        MemberVO v1 = MemberVO.builder()
                                .memberId("java")
                                .build();
        MemberVO v2 = MemberVO.builder()
                                .memberId("java")
                                .memberName("hong")
                                .build();
        MemberVO v3 = MemberVO.builder()
                                .memberName("hong")
                                .memberPw("1111")
                                .memberRoll("USER")
                                .build();
        MemberVO v4 = MemberVO.builder().build();
    }

}