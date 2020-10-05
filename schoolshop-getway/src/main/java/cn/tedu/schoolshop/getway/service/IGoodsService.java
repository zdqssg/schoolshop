package cn.tedu.schoolshop.getway.service;



import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家货物 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-25
 */
public interface IGoodsService extends IService<Goods> {
    List<GoodsShowVo> selectHotProducts();

    Map findGoodDetailById(Integer id, Integer pageNum);

    List<GoodsShowVo> findMerchantHotGoods(Integer merchantId);

    List<GoodsShowVo> findMerchantNewGoods(Integer merchantId);

    List<GoodsShowVo> selectRecommendGoods();

    GoodsShowVo findNewGood();
}
