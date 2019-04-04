package com.miaosha.controller;

import com.miaosha.controller.viewobject.AccountVO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.AccountService;
import com.miaosha.service.impl.AccountServiceImpl;
import com.miaosha.service.model.AccountModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller("account")
@RequestMapping("/account")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AccountController extends BaseController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "account", required = false) String account,
                                  @RequestParam(name = "encryptPassword", required = false) String password) throws BusinessException {
        //入参校验
        System.out.println(account + password);
        Map<String, String[]> map = httpServletRequest.getParameterMap();
        for (String s : map.keySet()) {
            System.out.println(s + "   " + map.get(s)[0]);
        }
        System.out.println(httpServletRequest.getParameterNames());
        if (org.apache.commons.lang3.StringUtils.isEmpty(account) ||
                org.apache.commons.lang3.StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        AccountModel accountModel = accountService.validateLogin(account, password);
        //用户登录服务，校验用户登录是否合法


        //将登陆凭证加入到用户登陆成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", accountModel);
        AccountVO accountVO = this.convertFromModel(accountModel);
        return CommonReturnType.create(accountVO);

    }

    private AccountVO convertFromModel(AccountModel accountModel) {
        AccountVO accountVO = new AccountVO();
        if (accountModel == null) {
            return null;
        }
        BeanUtils.copyProperties(accountModel, accountVO);
        return accountVO;

    }

    private String EncodeByMd5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
