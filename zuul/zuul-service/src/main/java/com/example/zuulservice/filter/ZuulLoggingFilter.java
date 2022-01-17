package com.example.zuulservice.filter;

// 로그를 출력해줄 수 있는 필터를 생성
// 추상 클래스를 상속 받아 필터 클래스를 인스턴스를 만들고 스프링 부트 안에 등록함
// 등록은 @Component 로 하면 됨

// 스프링에서 사용되는 bean(객체)들을 용도에 맞게 역할을 지정할 수 있음
// 용도가 아직 모를 때는 일반적인 형태로 등록하고 싶을 때 -> @Component 사용
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
public class ZuulLoggingFilter // extends ZuulFilter
{
    // ZuulFilter 안의 메서드 재정의 필요(오버라이드)

//    @Override
    public Object run() // throws ZuulException
     {
//        log.info("***************** printing logs : ");
        // requestContext -> zuul 에 있는 requestContext 를 써야함
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        log.info("*****************" + request.getRequestURI());
        return null;
    }

//    @Override
    public String filterType() {
        return "pre";
    }

//    @Override
    public int filterOrder() {
        return 1;
    }
//    @Override
    public boolean shouldFilter() {
        return true;
    }

}
