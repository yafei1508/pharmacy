package com.miaosha.service.impl;

import com.miaosha.dao.UserDAOMapper;
import com.miaosha.dao.UserPasswordDAOMapper;
import com.miaosha.dataobject.UserDAO;
import com.miaosha.dataobject.UserPasswordDAO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.UserService;
import com.miaosha.service.model.UserModel;
import com.miaosha.validator.ValidationResult;
import com.miaosha.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.validation.Validator;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAOMapper userDAOMapper;
    @Autowired
    private UserPasswordDAOMapper userPasswordDAOMapper;
    @Autowired
    private ValidatorImpl validator;
    @Override
    public UserModel getUserById(Integer id) {
        //
        UserDAO userDAO = userDAOMapper.selectByPrimaryKey(id);
        if(userDAO == null) {
            return null;
        }
        //通过用户id获取对应的加密密码
        UserPasswordDAO userPasswordDAO = userPasswordDAOMapper.selectByUserId(userDAO.getId());
        return convertFromDataObject(userDAO, userPasswordDAO);
    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if(userModel== null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //校验结果
        ValidationResult validationResult = validator.validate(userModel);
        if(validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validationResult.getErrMsg());
        }
        //校验成功后进行数据库操作
        UserDAO userDAO = convertFromDataObject(userModel);
        userDAOMapper.insertSelective(userDAO);
        userModel.setId(userDAO.getId());
        UserPasswordDAO userPasswordDAO = converPasswordFormModel(userModel);
        userPasswordDAOMapper.insertSelective(userPasswordDAO);

        return;
    }

    @Override
    public UserModel validateLogin(String telephone, String encrptassword) throws BusinessException {
        UserDAO userDAO = userDAOMapper.selectByTelephone(telephone);
        if(userDAO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDAO userPasswordDAO= userPasswordDAOMapper.selectByUserId(userDAO.getId());
        UserModel userModel = convertFromDataObject(userDAO,userPasswordDAO);
        if(!StringUtils.equals(encrptassword, userModel.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    private UserPasswordDAO converPasswordFormModel(UserModel userModel) {
        if(userModel == null) {
            return null;
        }
        UserPasswordDAO userPasswordDAO = new UserPasswordDAO();
        userPasswordDAO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDAO.setUserId(userModel.getId());
        return userPasswordDAO;

    }
    private UserDAO convertFromDataObject(UserModel userModel) {
        if(userModel == null) {
            return null;
        }
        UserDAO userDAO = new UserDAO();
        BeanUtils.copyProperties(userModel, userDAO);
        return userDAO;
    }

    private UserModel convertFromDataObject(UserDAO userDAO, UserPasswordDAO userPasswordDAO) {
        if(userDAO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDAO, userModel);
        if(userPasswordDAO != null) {
            userModel.setEncrptPassword(userPasswordDAO.getEncrptPassword());
        }
        return userModel;
    }
}
