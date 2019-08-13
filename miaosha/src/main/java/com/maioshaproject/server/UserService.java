package com.maioshaproject.server;

import com.maioshaproject.server.model.UserModel;

public interface  UserService  {

    //通过用户ID获取用户对象的方法
    UserModel getUserById(Integer id);

}
