package com.sun.service;

import com.github.pagehelper.PageHelper;
import com.sun.controller.dto.ResponseDto;
import com.sun.entity.Account;
import com.sun.mapper.AccountMapper;
import com.sun.utils.ConstantUtil;
import com.sun.utils.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    public String toMD5(String pwd) {
        String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        if (StringUtils.isEmpty(pwd)) {
            return "";
        }
        return md5Pwd;
    }

    public ResponseDto login(String accountName, String accountPwd, HttpSession session) {

        ResponseDto responseDto = new ResponseDto();
        if (StringUtils.isEmpty(accountName) || StringUtils.isEmpty(accountPwd)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("用户名密码不能为空");
            return responseDto;
        }
        Account account = accountMapper.selectByAccountName(accountName);
        if (ObjectUtils.isEmpty(account)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("无此用户名");
            return responseDto;
        }

        if (account.getBanded().equals(ConstantUtil.IS_BANDED)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("该账户已被封禁");
            return responseDto;
        }

        if (!account.getAccountPwd().equals(toMD5(accountPwd))) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("密码错误");
            return responseDto;
        }

        responseDto.setSuccess(ConstantUtil.IS_SUCCESS);
        responseDto.setAlertStr("登陆成功");
        responseDto.setAccount(account);
        session.setAttribute("account", account);
//        session.setAttribute("accountName", account.getAccountName());
//        session.setAttribute("role", account.getRole());
        return responseDto;
    }

    public ResponseDto register(String accountName, String accountPwd, String accountPwdRepeat, String securityCode) {
        ResponseDto responseDto = new ResponseDto();
        if (StringUtils.isEmpty(accountName) || StringUtils.isEmpty(accountPwd) || StringUtils.isEmpty(accountPwdRepeat)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("必填项不能为空");
            return responseDto;
        }
        if (accountName.length() > 16 || accountPwd.length() > 16) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("用户名密码长度不能超过16位");
            return responseDto;
        }
        if (!accountPwd.equals(accountPwdRepeat)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("两次密码输入的不一致");
            return responseDto;
        }
        String pwd = accountMapper.selectPwdByAccountName(accountName);
        if (!StringUtils.isEmpty(pwd)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("用户名已存在");
            return responseDto;
        }

        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountPwd(toMD5(accountPwd));
        if (StringUtils.isEmpty(securityCode)) {
            account.setRole(ConstantUtil.ORDINARY_ROLE);
        } else if (toMD5(securityCode).equals(ConstantUtil.SECURITY_CODE)) {
            account.setRole(ConstantUtil.ADMIN_ROLE);
        } else {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("安全码错误！普通用户不用填！");
            return responseDto;
        }

        if (accountMapper.insertAccount(account) > 0) {
            responseDto.setSuccess(ConstantUtil.IS_SUCCESS);
            responseDto.setAlertStr("注册成功");
            return responseDto;
        } else {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("未知错误");
            return responseDto;
        }
    }

    public ResponseDto changePwd(String accountName, String oldPwd, String newPwd, String repeatPwd) {
        ResponseDto responseDto = new ResponseDto();
        if (StringUtils.isEmpty(accountName) || StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(repeatPwd)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("必填项不能为空");
            return responseDto;
        }
        if (!newPwd.equals(repeatPwd)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("两次新密码必须相同");
            return responseDto;
        }
        if (newPwd.equals(oldPwd)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("新旧密码不能相同");
            return responseDto;
        }
        String realOldPwd = accountMapper.selectPwdByAccountName(accountName);
        if (StringUtils.isEmpty(realOldPwd)) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("无该用户");
            return responseDto;
        }
        if (!realOldPwd.equals(toMD5(oldPwd))) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("旧密码错误");
            return responseDto;
        }
        if (accountMapper.updatePwdByAccountName(accountName, toMD5(newPwd)) < 1) {
            responseDto.setSuccess(ConstantUtil.NO_SUCCESS);
            responseDto.setAlertStr("未知错误");
            return responseDto;
        }
        responseDto.setSuccess(ConstantUtil.IS_SUCCESS);
        responseDto.setAlertStr("密码修改成功");
        return responseDto;
    }

    public Map accountInfos(Integer pageNum) {
        List<Account> accounts;
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }

        Integer pageCount = accountMapper.selectNumByRole(ConstantUtil.ORDINARY_ROLE) / ConstantUtil.PAGE_SIZE + 1;
        PageHelper.startPage(pageNum, ConstantUtil.PAGE_SIZE);
        accounts = accountMapper.selectAllByRole(ConstantUtil.ORDINARY_ROLE);

        Map<String, Object> result = new HashMap<>();
        result.put("pageNum", pageNum);
        result.put("pageCount", pageCount);
        result.put("accounts", JsonUtil.toJson(accounts));

        return result;
    }
}
