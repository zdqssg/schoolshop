package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.GoodsMapper;
import cn.tedu.schoolshop.getway.service.IGoodsService;
import cn.tedu.schoolshop.model.Goods;

import cn.tedu.schoolshop.util.PageInfoUtils;
import cn.tedu.schoolshop.vo.goods.GoodDetailVo;
import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家货物 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-25
 */
@Service
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Value("${project.goodsComment.page-size}")
    private Integer pageSize;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsShowVo> selectHotProducts() {
        return goodsMapper.selectHotProducts();
    }

    @Override
    public Map findGoodDetailById(Integer id, Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<GoodDetailVo> goodDetailVos = goodsMapper.findGoodDetailById(id);
        if (goodDetailVos.size() == 0) {
            return null;
        }

        Map map = PageInfoUtils.oneJavaBeanToPageInfo(goodDetailVos);
        log.debug("list{}", map.get("list"));
        GoodDetailVo list = (GoodDetailVo) map.get("list");
        log.debug("商品名称{}", list.getGoodsClickCount());
        Integer clickCount = list.getGoodsClickCount();
        goodsMapper.updateById(new Goods().setId(id).setGoodsClickCount(++clickCount));
        return map;
    }

    @Override
    public List<GoodsShowVo> findMerchantHotGoods(Integer merchantId) {
        return goodsMapper.findMerchantHotGoods(merchantId);
    }

    @Override
    public List<GoodsShowVo> findMerchantNewGoods(Integer merchantId) {
        return goodsMapper.findMerchantNewGoods(merchantId);
    }

    @Override
    public List<GoodsShowVo> selectRecommendGoods() {
        return goodsMapper.selectRecommendGoods();
    }

    @Override
    public GoodsShowVo findNewGood() {
        return goodsMapper.findNewGood();
    }
}
