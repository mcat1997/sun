package com.sun.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Account {
    private Integer id;
    private String accountName;
    private String accountPwd;
    private Integer role;
    private Boolean isBanded;
    private Date createTime;
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Boolean getBanded() {
        return isBanded;
    }

    public void setBanded(Boolean banded) {
        isBanded = banded;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
