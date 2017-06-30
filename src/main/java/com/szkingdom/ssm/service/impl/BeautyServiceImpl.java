package com.szkingdom.ssm.service.impl;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.dao.BeautyMapper;
import com.szkingdom.ssm.entity.Beauty;
import com.szkingdom.ssm.service.BaseService;
import com.szkingdom.ssm.service.BeautyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianf on 2017/5/15.
 */

@Service
public class BeautyServiceImpl extends BaseService<Beauty> implements BeautyService {

    @Autowired
    BeautyMapper beautyMapper;



    @Override
    public EasyUIResult queryBeautyList(Integer page, Integer rows) {

        // 设置分页参数
        PageHelper.startPage(page, rows);

        // 查询Beauty数据
        Example example = new Example(Beauty.class);
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<Beauty> beautys = this.beautyMapper.selectByExample(example);

        // 获取分页后的信息
        PageInfo<Beauty> pageInfo = new PageInfo<Beauty>(beautys);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());

    }

    @Override
    public List<Beauty> queryBeautyList() {
        // 查询Beauty数据
        Example example = new Example(Beauty.class);
        example.setOrderByClause("updated DESC"); // 设置排序条件
        List<Beauty> beauties = this.beautyMapper.selectByExample(example);
        return beauties;
    }

    @Override
    public Beauty queryBeautyById(Long id) {
        return beautyMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean saveBeauty(Beauty beauty) {
        return beautyMapper.insert(beauty) == 1;
    }

    @Override
    public Boolean updateBeauty(Beauty beauty) {
        //return beautyMapper.updateByPrimaryKey(beauty) == 1;
        return beautyMapper.updateByPrimaryKeySelective(beauty) == 1;
    }

    @Override
    public Boolean deleteBeauty(Long id) {
        return beautyMapper.deleteByPrimaryKey(id) == 1;
    }



}
