package org.abhishek.om.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sca820 on 30 aug., 2022
 */
public class PersonAppInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(PersonAppInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MINIMAL: INTERCEPTOR PreHANDLE CALLED");
        logger.info("====== Person App PreHandle Interceptor to do authentication logic ======");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MINIMAL: INTERCEPTOR PostHANDLE CALLED");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MINIMAL: INTERCEPTOR AfterCompletionHANDLE CALLED");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
