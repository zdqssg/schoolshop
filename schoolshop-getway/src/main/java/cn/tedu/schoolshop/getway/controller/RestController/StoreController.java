package cn.tedu.schoolshop.getway.controller.RestController;


import cn.tedu.schoolshop.exception.service.NotFindException;
import cn.tedu.schoolshop.exception.service.StoreNotFindException;
import cn.tedu.schoolshop.getway.service.IMerchantService;
import cn.tedu.schoolshop.getway.service.IMerchantSlideService;
import cn.tedu.schoolshop.getway.utils.RedisUtils;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.merchantSlide.MerchantStoreSlideVo;
import cn.tedu.schoolshop.vo.store.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/26 10:27
 */
@RestController
@RequestMapping("/api-store")
@CrossOrigin
public class StoreController {
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private IMerchantSlideService slideService;

    /**
     * 商家Id StoreName   HeaderPhoto
     * @param merchantId
     * @return
     */
    @GetMapping("/{merchantId}")
    public R getIdStoreNameHeaderPhoto(@PathVariable("merchantId") Integer merchantId) {
        StoreVo storeVo = merchantService.findStoreById(merchantId);
        if (storeVo == null) {
            return R.failure(R.State.ERR_STORE_NOT_FIND, new StoreNotFindException("未找到商家"));
        }
        return R.ok(storeVo);
    }

    /**
     * 店铺轮播图
     * @param merchantId
     * @return
     */
    @GetMapping("/{merchantId}/slide")
    public R getMerchantSlide(@PathVariable("merchantId") Integer merchantId){
        List<MerchantStoreSlideVo> slide = slideService.findMerchantStoreSlide(merchantId);
        if (slide.size()==0){
            return R.failure(R.State.ERR_NOT_FIND,new NotFindException("资源未找到"));
        }else {
            return R.ok(slide);
        }

    }



}
