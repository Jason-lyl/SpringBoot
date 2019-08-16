package com.maioshaproject.controller;


import com.alibaba.druid.util.StringUtils;
import com.maioshaproject.controller.viewobject.UserVO;
import com.maioshaproject.error.BusinessException;
import com.maioshaproject.error.EmBusinessError;
import com.maioshaproject.response.CommonReturnType;
import com.maioshaproject.server.UserService;
import com.maioshaproject.server.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Name;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")//解决跨越
public class UserController extends  BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    //用户登录接口
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)){

            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);

        }

        //用户登录服务，用来校验用户是否合法
        UserModel userModel = userService.validateLogin(telphone, this.EncodeByMD5(password));

        //将登陆凭证加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);

    }


    //用户注册接口
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String optCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //验证手机号和对应otpCode相符合
        String inSesstionOtpCode =  (String)this.httpServletRequest.getSession().getAttribute(telphone);
        if(!com.alibaba.druid.util.StringUtils.equals(optCode, inSesstionOtpCode)){

           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不正确");

       }

       //用户的注册流程
        UserModel userModel = new UserModel();
       userModel.setTelphone(telphone);
       userModel.setName(name);
       userModel.setGender(new Byte(String.valueOf(gender.intValue())));
       userModel.setAge(age);
//       userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes())); 只支持16位

        userModel.setEncrptPassword(this.EncodeByMD5(password));
       userModel.setRegisterMode("byphone");
       userService.register(userModel);

        return  CommonReturnType.create(null);
    }


    public String EncodeByMD5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newStr = base64Encoder.encode(md5.digest(string.getBytes("utf-8")));
        return newStr;

    }



    //用户获取otp 短息接口
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone){
        //需要按照一定的规则生成OTP验证码

        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);

        //将OTP验证码同对应用户的手机号关联,使用htppsession的方式绑定它的手机号与OTPCode
        httpServletRequest.getSession().setAttribute(telphone, optCode);

        //将OTP验证码通过短信通道发送给用户，省略
        System.out.println("telephone = " + telphone + " & otpCode = " + optCode);

        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException{
        //调用service 服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if (userModel == null){
//            userModel.setEncrptPassword("111");
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域模型用户对象转化为可供UI使用的ViewObjectÍ
        UserVO userVO = convertFromModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVO);

    }

    private UserVO convertFromModel(UserModel userModel){
        if (userModel == null){
            return  null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }


}

