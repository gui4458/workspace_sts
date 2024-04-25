package com.green.Shop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 생성한 Interceptor 클래스의 적용 시점 정의
// WebMvcConfigurer 인터페이스를 구현 gn
// addInterceptors() 메서드를 오버라이딩
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // Interceptor 가 실행되는 url 지정
    // addInterceptor() : 이 메서드 안에 실행시킬 Interceptor 의 객체를 전달
    // addPathPatterns() : Interceptor 의 기능이 실행되는 url을 지정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getPrintInterceptor())
                .order(2)
//                .addPathPatterns("/**/**")
                .addPathPatterns("/item/**")
                        .excludePathPatterns("/item/test3");

        // *******중요******* 인터셉터는 비동기 통신 메서드에는 사용 불가!
        // 그래서 비동기로 실행되는 메서드는 반드시 인터셉터 실행에서 제외

        registry.addInterceptor(getDBInterceptor())
                .order(1)
                .addPathPatterns("/item/test1")
                .addPathPatterns("/item/test3");
    }
    // @Bean : 메서드으 ㅣ리턴 데이터를 객체로 생성
    @Bean
    public PrintInterceptor getPrintInterceptor(){

        return new PrintInterceptor();
    }
    @Bean
    public DBInterceptor getDBInterceptor(){
        return new DBInterceptor();
    }
}
