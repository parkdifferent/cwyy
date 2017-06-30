package com.szkingdom.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.dao.MyUserMapper;
import com.szkingdom.ssm.entity.MyUser;
import com.szkingdom.ssm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianf on 2016/12/15.
 */

@Service
public class MyUserServiceImpl implements MyUserService {

    @Autowired
    private MyUserMapper myUserMapper;

    @Override
    public EasyUIResult queryUserList(Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询User数据
        Example example = new Example(MyUser.class);
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<MyUser> users = this.myUserMapper.selectByExample(example);

        // 获取分页后的信息
        PageInfo<MyUser> pageInfo = new PageInfo<MyUser>(users);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());

       /* // 设置分页参数
        PageHelper.startPage(page, rows);
        // 查询User数据
        List<MyUser> users = this.myUserMapper.select(null);
        // 获取分页后的信息
        PageInfo<MyUser> pageInfo = new PageInfo<MyUser>(users);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());*/
    }

    @Override
    public MyUser queryUserById(Long id) {
        return this.myUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean saveUser(MyUser myUser) {
        return this.myUserMapper.insert(myUser) == 1;
    }

    @Override
    public Boolean updateUser(MyUser myUser) {
        return this.myUserMapper.updateByPrimaryKeySelective(myUser) == 1;
    }

    @Override
    public Boolean deleteUser(Long id) {
        return this.myUserMapper.deleteByPrimaryKey(id) == 1;
    }
}
