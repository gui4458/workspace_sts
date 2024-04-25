package com.green.Shop.interceptor;

import com.green.Shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// Interceptor 클래스
// HandlerInterceptor 인터페이스를 구현한 클래스는 Interceptor 클래스로 인식
// 해당 클래스에는 반복되는 기능을 정의
// 반복되는 기능의 호출 시점에 따라서
// HandlerInterceptor 인터페이스의 메서드를 오버라이딩
// 오버라이딩 할 수 있는 메서드
// preHandel() : controller 안의 메서드 실행 직전에 호출
// postHandel() : controller 안의 메서드 실행 후 html 로 가기 전에 호출
// afterCompletion() : controller 안의 메서드 실행 후 html까지 다 실행되면 호출
public class PrintInterceptor implements HandlerInterceptor {
    // preHandle
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle() 메소드 실행");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle() 메소드 실행");

        // 컨트롤러에 서전달한 데이터 확인
        Map<String,Object> modelData = modelAndView.getModel();

        String name = modelData.get("name").toString();
        MemberVO member = (MemberVO)modelData.get("member");

        modelAndView.addObject("addr","울산");

    }
}
