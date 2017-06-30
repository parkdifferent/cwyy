package com.szkingdom.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.dao.UserMapper;
import com.szkingdom.ssm.entity.MyUser;
import com.szkingdom.ssm.entity.User;
import com.szkingdom.ssm.service.BaseService;
import com.szkingdom.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianf on 2017/5/15.
 */

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    UserMapper userMapper;



    @Override
    public EasyUIResult queryUserList(Integer page, Integer rows) {

        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询User数据
        Example example = new Example(User.class);
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<User> users = this.userMapper.selectByExample(example);

        // 获取分页后的信息
        PageInfo<User> pageInfo = new PageInfo<User>(users);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());

    }

    @Override
    public User queryUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean saveUser(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public Boolean updateUser(User user) {
        //return userMapper.updateByPrimaryKey(user) == 1;
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    @Override
    public Boolean deleteUser(Long id) {
        return userMapper.deleteByPrimaryKey(id) == 1;
    }



}
