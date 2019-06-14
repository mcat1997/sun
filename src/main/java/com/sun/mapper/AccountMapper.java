package com.sun.mapper;

import com.sun.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account> selectAll();

    List<Account> selectAllByRole(@Param("role") Integer role);

    Integer selectNumByRole(@Param("role") Integer role);

    Account selectByAccountName(@Param("accountName") String accountName);

    String selectPwdByAccountName(@Param("accountName") String accountName);

    Integer insertAccount(@Param("account") Account account);

    Integer updatePwdByAccountName(@Param("accountName") String accountName, @Param("accountPwd") String accountPwd);

    Integer updateBandedById(@Param("id") Integer id, @Param("isBanded") Boolean isBanded);
}
