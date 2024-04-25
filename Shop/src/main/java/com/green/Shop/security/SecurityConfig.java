package com.green.Shop.security;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.Security;

//인증 인가에 대한 설정을 위한 클래스
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private LoginFailHandler loginFailHandler;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    // 인증과 인가에 대한 설정 내용이 있는 메소드 구현
    // 반드시 리턴타입은 SecurityFilterChain
    // 메소드의 매개변수로 HttpSecurity 가 필요
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws  Exception{

        security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        c -> {

                            c.anyRequest().permitAll();


//                            c.requestMatchers(
//                                    new AntPathRequestMatcher("/"),
//                                    new AntPathRequestMatcher("/item/list"),
//                                    new AntPathRequestMatcher("/member/join"),
//                                    new AntPathRequestMatcher("/member/login")
//                                    ).permitAll()
//                                    .requestMatchers(
//                                            new AntPathRequestMatcher("/admin/**")
//                                    ).hasRole("ADMIN")
//                                    .anyRequest().authenticated();
                        }
                ).formLogin(
                        formLogin -> {
                            formLogin.loginPage("/member/login")
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    .loginProcessingUrl("/member/goLogin")
//                                    .defaultSuccessUrl("/member/loginResult")
//                                    .failureUrl("/member/login")
                                    .successHandler(loginSuccessHandler) // 로그인 성공 시 실행 시킬 클래스의 객체
                                    .failureHandler(loginFailHandler); // 로그인 실패 시 실행 시킬 클래스의 객체
                        }
                ).logout(
                        logout -> {
                            // 해당 url 요청이 들어오면 시큐리티가 로그아웃 진행
                            logout.logoutUrl("/member/logout")
                                    // 로그아웃 성공 시 이동할 url
                                    .logoutSuccessUrl("/")
                                    // 로그아웃 성공 시 세션 데이터 삭제
                                    .invalidateHttpSession(true);
                        }
                );
        return security.build();
    }

    // 정적인 자원은 security 가 인증 및 인가 관리에서 제외하도록 설정
    // 정적인 파일 : .js, .css, 이미지
    // resources 폴더 밑에 있는 모든 파일들
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/upload/**"),
                new AntPathRequestMatcher("/favicon.ico")
        );
    }
}
