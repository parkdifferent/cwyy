package com.szkingdom.ssm.service;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.Admin;

import javax.servlet.http.HttpSession;

/**
 * Created by tianf on 2017/5/14.
 */
public interface AdminService {

    public EasyUIResult queryAdminList(Integer page, Integer rows);

    public Admin queryAdminById(Long id);

    public Boolean saveAdmin(Admin admin);

    public Boolean updateAdmin(Admin admin);

    public Boolean deleteAdmin(Long id);


    public Admin queryAdminByUserName(Admin admin);

    public Admin queryAdminByNameAndPassword(Admin admin);

    public EasyUIResult queryAdminList(Integer page, Integer rows, HttpSession session);
}
