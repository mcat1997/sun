package com.sun.controller.Interceptor;

import com.sun.entity.Account;
import com.sun.utils.ConstantUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Account account = (Account) request.getSession().getAttribute("account");
        if (ObjectUtils.isEmpty(account)){
            response.sendRedirect(request.getContextPath()+"/account");
            return false;
        }
        if (!account.getRole().equals(ConstantUtil.ADMIN_ROLE)){
            System.out.println("跳转");
            response.sendRedirect(request.getContextPath()+"/ordinary");
            return false;
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
