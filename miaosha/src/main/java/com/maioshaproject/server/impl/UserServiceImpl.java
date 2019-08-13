package com.maioshaproject.server.impl;

import com.maioshaproject.dao.UserDOMapper;
import com.maioshaproject.dao.UserPasswordDOMapper;
import com.maioshaproject.dataObject.UserDO;
import com.maioshaproject.dataObject.UserPasswordDO;
import com.maioshaproject.server.UserService;
import com.maioshaproject.server.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id){

        //调用userdomapper获取到对应的用户dataObject
        UserDO userDO =  userDOMapper.selectByPrimaryKey(1);

        if (userDO == null){
            return null;
        }
        //通过用户id获取对应的用户加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO, userPasswordDO);


    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if (userDO == null){
            return  null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);

        if (userPasswordDO != null){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return  userModel;
    }
}
