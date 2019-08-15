package com.maioshaproject.controller;


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

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Name;
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


    //用户注册接口
    @RequestMapping(value = "/regist", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String optCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException {

        //验证手机号和对应otpCode相符合
       String inSesstionOtpCode =  this.httpServletRequest.getSession().getAttribute(telphone).toString();
       if(!com.alibaba.druid.util.StringUtils.equals(optCode, inSesstionOtpCode)){

           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不正确");

       }

       //用户的注册流程
        UserModel userModel = new UserModel();
       userModel.setTelphone(telphone);
       userModel.setName(name);
       userModel.setGender(gender);
       userModel.setAge(age);
       userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));
       userModel.setRegisterMode("byphone");

       userService.register(userModel);

        return  CommonReturnType.create(null);
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

