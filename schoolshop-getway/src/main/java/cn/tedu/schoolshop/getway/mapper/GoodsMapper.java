package cn.tedu.schoolshop.getway.mapper;



import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.vo.goods.GoodDetailVo;
import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商家货物 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-25
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsShowVo> selectHotProducts();

    List<GoodDetailVo> findGoodDetailById(Integer id);

    List<GoodsShowVo> findMerchantHotGoods(Integer merchantId);

    List<GoodsShowVo> findMerchantNewGoods(Integer merchantId);

    List<GoodsShowVo> selectRecommendGoods();

    GoodsShowVo findNewGood();
}
