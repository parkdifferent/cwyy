package com.szkingdom.ssm.service;

import com.szkingdom.ssm.bean.EasyUIResult;
import com.szkingdom.ssm.entity.Beauty;

import java.util.List;

/**
 * Created by tianf on 2017/5/15.
 */
public interface BeautyService {

    public EasyUIResult queryBeautyList(Integer page, Integer rows);

    public List<Beauty> queryBeautyList();

    public Beauty queryBeautyById(Long id);

    public Boolean saveBeauty(Beauty beauty);

    public Boolean updateBeauty(Beauty beauty);

    public Boolean deleteBeauty(Long id);

}
