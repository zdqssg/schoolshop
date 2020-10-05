package cn.tedu.schoolshop.getway.service;


import cn.tedu.schoolshop.model.MerchantSlide;
import cn.tedu.schoolshop.vo.merchantSlide.MerchantStoreSlideVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商家轮播图 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-26
 */
public interface IMerchantSlideService extends IService<MerchantSlide> {
    List<MerchantStoreSlideVo> findMerchantStoreSlide(Integer merchantId);
}
