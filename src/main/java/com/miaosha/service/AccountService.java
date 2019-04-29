package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.AccountModel;

public interface AccountService {
    AccountModel validateLogin(String account, String encrptPassword)throws BusinessException;
    AccountModel getAccountById(Integer id);
}
