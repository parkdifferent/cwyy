package com.szkingdom.ssm.service;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.MyUser;

/**
 * Created by tianf on 2016/12/15.
 */
public interface MyUserService {

    public EasyUIResult queryUserList(Integer page, Integer rows);

    public MyUser queryUserById(Long id);

    public Boolean saveUser(MyUser myUser);

    public Boolean updateUser(MyUser myUser);

    public Boolean deleteUser(Long id);
}
