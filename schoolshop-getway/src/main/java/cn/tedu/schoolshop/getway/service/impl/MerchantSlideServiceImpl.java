package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.MerchantSlideMapper;
import cn.tedu.schoolshop.getway.service.IMerchantSlideService;
import cn.tedu.schoolshop.model.MerchantSlide;
import cn.tedu.schoolshop.vo.merchantSlide.MerchantStoreSlideVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家轮播图 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-26
 */
@Service
public class MerchantSlideServiceImpl extends ServiceImpl<MerchantSlideMapper, MerchantSlide> implements IMerchantSlideService {
    @Autowired
    private MerchantSlideMapper merchantSlideMapper;
    @Override
    public List<MerchantStoreSlideVo> findMerchantStoreSlide(Integer merchantId) {
        List<MerchantStoreSlideVo> slide = merchantSlideMapper.findMerchantStoreSlide(merchantId);
        return slide;
    }
}
