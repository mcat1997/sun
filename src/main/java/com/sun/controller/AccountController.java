package com.sun.controller;

import com.sun.controller.dto.ResponseDto;
import com.sun.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("")
    public ModelAndView account(HttpSession session) {
        session.removeAttribute("account");
        return new ModelAndView("account");
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseDto login(
            @RequestParam("accountName") String accountName,
            @RequestParam("accountPwd") String accountPwd,
            HttpSession session
    ) {
        return accountService.login(accountName, accountPwd, session);
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseDto register(
            @RequestParam("accountName") String accountName,
            @RequestParam("accountPwd") String accountPwd,
            @RequestParam("accountPwdRepeat") String accountPwdRepeat,
            @RequestParam("securityCode") String securityCode
    ) {
        return accountService.register(accountName, accountPwd, accountPwdRepeat, securityCode);
    }

    @ResponseBody
    @PostMapping("/changePwd")
    public ResponseDto changePwd(
            @RequestParam("accountName") String accountName,
            @RequestParam("oldPwd") String oldPwd,
            @RequestParam("newPwd") String newPwd,
            @RequestParam("repeatPwd") String repeatPwd
    ) {
        return accountService.changePwd(accountName, newPwd, oldPwd, repeatPwd);
    }
}
