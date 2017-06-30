package com.szkingdom.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.dao.AdminMapper;
import com.szkingdom.ssm.entity.Admin;
import com.szkingdom.ssm.service.AdminService;
import com.szkingdom.ssm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tianf on 2017/5/14.
 */

@Service
public class AdminServiceImpl extends BaseService<Admin> implements AdminService {


    @Autowired
    AdminMapper adminMapper;


    @Override
    public EasyUIResult queryAdminList(Integer page, Integer rows) {

        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询User数据
        Example example = new Example(Admin.class);
        //example.createCriteria().andEqualTo("id",sess)
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<Admin> admins = adminMapper.selectByExample(example);

        // 获取分页后的信息
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public Admin queryAdminById(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean saveAdmin(Admin admin) {
        return adminMapper.insert(admin) ==1;
    }

    @Override
    public Boolean updateAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin) ==1;
    }

    @Override
    public Boolean deleteAdmin(Long id) {
        return adminMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public Admin queryAdminByUserName(Admin admin) {
        return adminMapper.selectOne(admin);

    }

    @Override
    public Admin queryAdminByNameAndPassword(Admin admin) {
        return adminMapper.selectOne(admin);
    }

    @Override
    public EasyUIResult queryAdminList(Integer page, Integer rows, HttpSession session) {
        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询User数据
        Example example = new Example(Admin.class);
        example.createCriteria().andEqualTo("id",session.getAttribute("id"));
        //example.setOrderByClause("updated DESC"); // 设置排序条件
        List<Admin> admins = adminMapper.selectByExample(example);

        // 获取分页后的信息
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }


}
