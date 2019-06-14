package com.sun.service;

import com.sun.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Resource
    private AccountService accountService;

    @Test
    public void login() {
        HttpSession session = new HttpSession() {
            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int interval) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public Object getValue(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public void putValue(String name, Object value) {

            }

            @Override
            public void removeAttribute(String name) {

            }

            @Override
            public void removeValue(String name) {

            }

            @Override
            public void invalidate() {

            }

            @Override
            public boolean isNew() {
                return false;
            }
        };
        System.out.println(JsonUtil.toJson(accountService.login("liuxiao", "",session)));
        System.out.println(JsonUtil.toJson(accountService.login("", "123",session)));
        System.out.println(JsonUtil.toJson(accountService.login("liuxiao", "123",session)));
        System.out.println(JsonUtil.toJson(accountService.login("liu", "213",session)));
        System.out.println(JsonUtil.toJson(accountService.login("liu", "123",session)));
    }

    @Test
    public void register() {
        System.out.println(JsonUtil.toJson(accountService.register(
                "",
                "",
                "",
                "")));
        System.out.println(JsonUtil.toJson(accountService.register(
                "liu",
                "123",
                "222",
                "")));
        System.out.println(JsonUtil.toJson(accountService.register(
                "liu",
                "123",
                "123",
                "")));
        System.out.println(JsonUtil.toJson(accountService.register(
                "liuxiao1",
                "123",
                "123",
                "1")));
        System.out.println(JsonUtil.toJson(accountService.register(
                "liuxiao1",
                "123",
                "123",
                "")));
        System.out.println(JsonUtil.toJson(accountService.register(
                "liuxiao2",
                "123",
                "123",
                "385469721")));
    }

    @Test
    public void toMD5() {
        System.out.println(JsonUtil.toJson(accountService.toMD5("321")));
    }

    @Test
    public void changePwd() {
        System.out.println(JsonUtil.toJson(accountService.changePwd(
                "liu",
                "321",
                "123",
                "123")));

    }
}
