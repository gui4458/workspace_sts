package com.green.Shop.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

// 로그인 성공 시 실행되는 클래스
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("로그인 성공");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        // 로그인 성공 시 이동할 페이지
        setDefaultTargetUrl("/");

        ////////////////////////////////
        // --- 로그인 시 필요한 코드 여기서 작성
        // ex > 세션에 데이터 저장
        HttpSession session = request.getSession();
//        session.getAttribute();
//        session.setAttribute();
        ////////////////////////////////

        // 로그인 정보를 통한 로직 구성
        User user = (User)authentication.getPrincipal();
        user.getUsername();// 로그인한 사람의 ID
        List<GrantedAuthority> authoList = new ArrayList<>(user.getAuthorities());
        List<String> aList = new ArrayList<>();
        for (GrantedAuthority authority : authoList){
            aList.add(authority.getAuthority());
        }
        boolean b = aList.contains("ADMIN");

        if (b){
            redirectStrategy.sendRedirect(request, response, "/admin/updateItem");

        }
        // 이전 페이지 혹은 가려던 페이지가 있는 경우
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
        response.sendRedirect("/member/loginResult");
    }
}
