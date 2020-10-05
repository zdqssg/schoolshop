package cn.tedu.schoolshop.api.merchant.controller;

import cn.tedu.schoolshop.api.merchant.service.Category3Service;
import cn.tedu.schoolshop.api.merchant.service.IMerchantService;
import cn.tedu.schoolshop.api.merchant.service.IUserOrderService;
import cn.tedu.schoolshop.api.merchant.service.IUserLikeStoreService;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.Merchant;
import cn.tedu.schoolshop.security.LoginMerchantInfo;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.Category3Vo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 11:03
 */
@RestController
@RequestMapping("/info")
@CrossOrigin
@Slf4j
public class MerchantInfoController {
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private Category3Service category3Service;

    @Autowired
    private IUserLikeStoreService likeStoreService;

    @GetMapping("")
    public R info(@AuthenticationPrincipal LoginMerchantInfo merchantInfo) {
        System.out.println(merchantInfo.getNickname());
        return R.ok(merchantInfo);
    }
    @GetMapping("/category3")
    public R category3(@AuthenticationPrincipal LoginMerchantInfo merchantInfo) {
        List<Category3Vo> category3Vos = category3Service.selectByCategory2Id(merchantInfo.getCategory2Id());
        return R.ok(category3Vos);
    }
    @GetMapping("/clickCountAndSumMoney")
    public R clickCountAndSumMoney(@AuthenticationPrincipal LoginMerchantInfo merchantInfo) {
        Merchant merchant = merchantService.selectClickCountAndSumMoneyById(merchantInfo.getId());
        return R.ok(merchant);
    }

    @GetMapping("/likeNumber")
    public R likeNumber(@AuthenticationPrincipal LoginMerchantInfo merchantInfo) {
        int i = likeStoreService.selectCountByMerchantId(merchantInfo.getId());
        return R.ok(i);
    }

    @PutMapping("/changeHeadPhoto/{storeHeadPhoto}")
    public R changeHeadPhoto(@AuthenticationPrincipal LoginMerchantInfo merchantInfo,
                             @PathVariable("storeHeadPhoto")String storeHeadPhoto){
        boolean update = merchantService.updateById(new Merchant().setId(merchantInfo.getId()).setStoreHeadPhoto(storeHeadPhoto));
        if (update){
            return R.ok();
        }else {
            return R.failure(R.State.ERR_UPDATE,new UpdateExteption("系统异常"));
        }
    }
}
