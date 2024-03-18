package com.green.Shop.security;

import com.green.Shop.member.service.MemberService;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name="memberService")
    private MemberService memberService;
    @Override
    public UserDetails loadUserByUsername(String username){
        // 로그인을 시도하는 유저의 정보 (id, pw, 권한)를 조회
        MemberVO member = memberService.login(username);
        if (member == null){
//            throw new InternalAuthenticationServiceException("errorrrrrrrrrrrrrrrrrrrr");
            throw new BadCredentialsException("errorrrrrrrrrrrrrrrrrrr");
        }
        User userInfo = (User) User.builder()
                .username(member.getMemberId())
                .password("{noop}"+member.getMemberPw())
                .roles(member.getMemberRoll())
                .build();
        return userInfo;
    }
}
