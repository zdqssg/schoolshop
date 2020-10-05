package cn.tedu.schoolshop.api.merchant.mapper;


import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.vo.goods.GoodsCategoryIdMerchantIdVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商家货物 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> findAllByMerchantId(GoodsCategoryIdMerchantIdVo vo);
}
