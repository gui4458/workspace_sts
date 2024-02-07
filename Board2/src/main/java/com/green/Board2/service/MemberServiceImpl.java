package com.green.Board2.service;

import com.green.Board2.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate SqlSession;


    @Override
    public void joinMember(MemberVO memberVO) {
        SqlSession.insert("memberMapper.joinMember", memberVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return SqlSession.selectOne("memberMapper.login", memberVO);

    }
}
