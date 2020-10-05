package cn.tedu.schoolshop.getway;

import cn.tedu.schoolshop.getway.mapper.GoodsCommentMapper;
import cn.tedu.schoolshop.getway.mapper.MerchantSlideMapper;
import cn.tedu.schoolshop.getway.service.IHomeSlideService;
import cn.tedu.schoolshop.model.GoodsComment;
import cn.tedu.schoolshop.vo.home.HomeSlideVo;
import cn.tedu.schoolshop.vo.merchantSlide.MerchantStoreSlideVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
class SchoolshopGatewayApplicationTests {
    @Autowired
    private GoodsCommentMapper commentMapper;
    @Autowired
    MerchantSlideMapper merchantSlideMapper;
    @Autowired
    IHomeSlideService homeSlideService;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Test
    void contextLoads() {
        PageHelper.startPage(1, 5);
        List<GoodsComment> goodsCommentList = commentMapper.selectByGoodId(49);
        PageInfo<GoodsComment> pageInfo = new PageInfo<>(goodsCommentList);
        System.err.println(pageInfo);
    }
    @Test
    void merchantSlideMapper(){
        List<MerchantStoreSlideVo> slide = merchantSlideMapper.findMerchantStoreSlide(15);
        log.debug("商家轮播图{}",slide);
    }

    @Test
    void homeSlideService(){
        List<HomeSlideVo> homeSlideVos = homeSlideService.selectShowAll();
        for (HomeSlideVo vo : homeSlideVos) {
            log.debug("首页轮播图{}",vo);
        }
    }

}
