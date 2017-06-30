package com.szkingdom.ssm.service;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.MyUser;
import com.szkingdom.ssm.entity.User;

import java.util.List;

/**
 * Created by tianf on 2017/5/15.
 */
public interface UserService {

    public EasyUIResult queryUserList(Integer page, Integer rows);

    public User queryUserById(Long id);

    public Boolean saveUser(User user);

    public Boolean updateUser(User user);

    public Boolean deleteUser(Long id);

}
