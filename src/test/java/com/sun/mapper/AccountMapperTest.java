package com.sun.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.sun.entity.Account;
import com.sun.service.AccountService;
import com.sun.utils.ConstantUtil;
import com.sun.utils.JsonUtil;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountService accountService;

    @Test
    public void selectAll() {

        System.out.println("select all");
        PageHelper.startPage(1,3);
        List<Account> accounts = accountMapper.selectAll();
        accounts.forEach(account -> {
            System.out.println(JsonUtil.toJson(account));
        });
    }

    @Test
    public void selectPwdByUsername() {
        System.out.println(accountMapper.selectPwdByAccountName("liuxiao"));
        System.out.println(accountMapper.selectPwdByAccountName("111"));
    }

    @Test
    public void insertAccount() {
        Account account = new Account();
        account.setAccountName("liuxiao");
        account.setAccountPwd("202cb962ac59075b964b07152d234b70");
        account.setBanded(ConstantUtil.IS_BANDED);
        account.setRole(ConstantUtil.ADMIN_ROLE);
        if (accountMapper.insertAccount(account) == 1) {
            accountMapper.selectAll()
                    .forEach(account1 -> System.out.println(JsonUtil.toJson(account1)));
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void selectByAccountName() {
        System.out.println(JsonUtil.toJson(accountMapper.selectByAccountName("liu")));
        System.out.println(JsonUtil.toJson(accountMapper.selectByAccountName("xiao")));
        System.out.println(JsonUtil.toJson(accountMapper.selectByAccountName("")));
    }

    @Test
    public void updatePwdByAccountName() {
        accountMapper.updatePwdByAccountName("liu", accountService.toMD5("321"));
        accountMapper.updatePwdByAccountName("1", accountService.toMD5("321"));
        accountMapper.updatePwdByAccountName("", accountService.toMD5("321"));
    }

    @Test
    public void selectNumByRole() {
        System.out.println(accountMapper.selectNumByRole(ConstantUtil.ORDINARY_ROLE));
    }

    @Test
    public void selectAllByRole() {
        accountMapper.selectAllByRole(ConstantUtil.ORDINARY_ROLE).forEach(account -> System.out.println(JsonUtil.toJson(account)));
        System.out.println();
        accountMapper.selectAllByRole(ConstantUtil.ADMIN_ROLE).forEach(account -> System.out.println(JsonUtil.toJson(account)));
        System.out.println();
        accountMapper.selectAllByRole(null).forEach(account -> System.out.println(JsonUtil.toJson(account)));
    }

    @Test
    public void updateBandedById() {
        accountMapper.updateBandedById(15,ConstantUtil.NO_BANDED);
    }

}
