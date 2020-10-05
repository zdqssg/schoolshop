package cn.tedu.schoolshop.getway.mapper;


import cn.tedu.schoolshop.model.MerchantSlide;
import cn.tedu.schoolshop.vo.merchantSlide.MerchantStoreSlideVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商家轮播图 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-26
 */
@Repository
public interface MerchantSlideMapper extends BaseMapper<MerchantSlide> {
    List<MerchantStoreSlideVo> findMerchantStoreSlide(Integer merchantId);
}
