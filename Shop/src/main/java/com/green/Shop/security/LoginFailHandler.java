package com.green.Shop.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// 로그인 실패 시 실행되는 클래스
// AuthenticationFailureHandler 라는 인터페이스를 구현한 클래스를 생성하면
// onAuthenticationFailure() 라는 메소드를 정의해야 한다.
// 이 메서드가 로그인 실패 시 자동으로 호출된다.
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    // 로그인 실패 시 자동으로 호출되는 메소드
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(exception.fillInStackTrace());
        String errorMsg = "";
        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMsg = "아이디 또는 비밀번호가 틀립니다.";
        }else if (exception instanceof UsernameNotFoundException){
            errorMsg = "존재하지 않는 사용자 ID 입니다.";
        }else{
            errorMsg = "알수 없는 이유로 로그인 실패 했습니다.\n관리자에게 문의하세요";
        }
        //한글 인코딩 변환
        errorMsg = URLEncoder.encode(errorMsg, StandardCharsets.UTF_8);
        response.sendRedirect("/member/login?errorMsg="+errorMsg);

    }
}
