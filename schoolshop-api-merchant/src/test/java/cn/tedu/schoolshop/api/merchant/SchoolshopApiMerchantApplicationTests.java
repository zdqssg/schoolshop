package cn.tedu.schoolshop.api.merchant;

import cn.tedu.schoolshop.api.merchant.mapper.Category3Mapper;
import cn.tedu.schoolshop.vo.Category3Vo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolshopApiMerchantApplicationTests {
   @Autowired
    Category3Mapper category3Mapper;
    @Test
    void contextLoads() {
        List<Category3Vo> category3Vos = category3Mapper.selectByCategory2Id(1);
        System.out.println(category3Vos);
    }

}
