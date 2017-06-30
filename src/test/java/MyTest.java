import com.szkingdom.ssm.bean.PetBeautyDTO;
import com.szkingdom.ssm.controller.AdminController;
import com.szkingdom.ssm.dao.PetMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by phoenix on 2017/6/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:application.xml"})
public class MyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTest.class);

    @Autowired
    PetMapper petMapper;

    @Test
    public void test() {
        Date first = new Date();
        List<PetBeautyDTO> petBeautyDTOList= petMapper.queryPetBeautyInfo(0,30);
        LOGGER.info("first quest costs:"+ (new Date().getTime()-first.getTime()) +" ms");

        Date second = new Date();
        List<PetBeautyDTO> petBeautyDTOList1= petMapper.queryPetBeautyInfo(0,30);
        LOGGER.info("second quest costs:"+ (new Date().getTime()-second.getTime()) +" ms");

    }
}
