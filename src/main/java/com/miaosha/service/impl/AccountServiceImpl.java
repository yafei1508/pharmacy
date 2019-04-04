package com.miaosha.service.impl;

import com.miaosha.dao.UserDOMapper;
import com.miaosha.dataobject.UserDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.AccountService;
import com.miaosha.service.model.AccountModel;
import com.miaosha.validator.ValidatorImpl;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private ValidatorImpl validator;

    @Override
    public AccountModel validateLogin(String account, String encrptPassword) throws BusinessException {
        UserDO userDO = userDOMapper.selectByAccount(account);
        if(userDO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        AccountModel accountModel = convertFromDataObject(userDO);
        if(!org.apache.commons.lang3.StringUtils.equals(encrptPassword, accountModel.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return accountModel;
    }

    private AccountModel convertFromDataObject(UserDO userDO) {
        if(userDO == null) {
            return null;
        }
        AccountModel accountModel = new AccountModel();
        BeanUtils.copyProperties(userDO, accountModel);
        return accountModel;
    }

    @Override
    public AccountModel getAccountById(Integer id) {
        return null;
    }
}
