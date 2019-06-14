package com.sun.controller.dto;

import com.sun.entity.Account;
import com.sun.entity.Questionnaire;

import java.util.List;
import java.util.Map;

public class ResponseDto {
    private boolean isSuccess; //是否成功
    private String alertStr; //提示信息
    private Account account; //用户
    private Questionnaire questionnaire;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getAlertStr() {
        return alertStr;
    }

    public void setAlertStr(String alertStr) {
        this.alertStr = alertStr;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}
