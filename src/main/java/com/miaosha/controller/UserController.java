package com.miaosha.controller;

import com.miaosha.controller.viewobject.UserVO;
import com.miaosha.dataobject.UserDAO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.UserService;
import com.miaosha.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone")String telephone,
                                  @RequestParam(name = "password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(org.apache.commons.lang3.StringUtils.isEmpty(telephone) ||
                org.apache.commons.lang3.StringUtils.isEmpty(password) ){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //用户登录服务，校验用户登录是否合法
        UserModel userModel = userService.validateLogin(telephone, this.EncodeByMd5(password));


        //将登陆凭证加入到用户登陆成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);

    }

    //用户注册
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") String gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和对应的otpCode是否相符合
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        System.out.println("inSessionOtpCode:"  + inSessionOtpCode + "  otpCode : " + otpCode);
        if (!com.alibaba.druid.util.StringUtils.equals(otpCode, inSessionOtpCode))
        {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不合法");
        }
        //用户对应的注册流程
        UserModel userModel = new UserModel();
        userModel.setTelephone(telephone);
        userModel.setAge(age);
        userModel.setName(name);
        if(gender.equals("true")) {
            userModel.setGender(true);
        }else {
            userModel.setGender(false);
        }
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("byphone");
        userModel.setThirdPartyId(telephone);
        userModel.setEncrptPassword(EncodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);




    }
//
//    public String EncodeByMd5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        BASE64Encoder encoder = new BASE64Encoder();
//        String newStr = encoder.encode(messageDigest.digest(string.getBytes("utf-8")));
//        return newStr;
//    }

    public static String EncodeByMd5(String plainText) {
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

    @RequestMapping(value = "/getotp", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telephone") String telephone) {
        //按照一定规则生成otp验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = Integer.toString(randomInt);

        //将otp验证码和用户关联,使用httpsession关联的方式实现
        httpServletRequest.getSession().setAttribute(telephone, otpCode);


        //将otp验证码发送给用户
        System.out.println("telephone " + telephone + " & otpCode " + otpCode);

        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用service服务的获取对应id的用户并返回前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (userModel == null)
            throw new BusinessException(EmBusinessError.USER_NOTE_EXIST);
        UserVO userVO = convertFromModel(userModel);
        //将核心领域模型转化为可供UI使用的view object
        return CommonReturnType.create(userVO);
    }


    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }


}
