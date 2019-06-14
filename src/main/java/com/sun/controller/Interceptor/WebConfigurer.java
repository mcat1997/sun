package com.sun.controller.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;
    @Resource
    AdminInterceptor adminInterceptor;
    @Resource
    OrdinaryInterceptor ordinaryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/subjectAnswerInfos");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin", "/accountInfos", "/questionnaireInfos2", "/banItem", "/editQuestionnaire2");
        registry.addInterceptor(ordinaryInterceptor)
                .addPathPatterns("/ordinary", "/insertOneQuestionnaire", "/questionnaireInfos", "/editQuestionnaire");
    }

}