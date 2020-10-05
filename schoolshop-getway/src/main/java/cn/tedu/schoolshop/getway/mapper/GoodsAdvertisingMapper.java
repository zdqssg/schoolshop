package cn.tedu.schoolshop.getway.mapper;


import cn.tedu.schoolshop.model.GoodsAdvertising;
import cn.tedu.schoolshop.vo.GoodsAdvertising.GoodsAdvertisingVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
@Repository
public interface GoodsAdvertisingMapper extends BaseMapper<GoodsAdvertising> {
      List<GoodsAdvertisingVo> selectPageHelper();

    GoodsAdvertisingVo findOne(Integer advertisingID);

    List<GoodsAdvertisingVo> selectLimit(Integer pageNum);

    List<GoodsAdvertisingVo> selectByMerchantId(Integer merchantId);
}
