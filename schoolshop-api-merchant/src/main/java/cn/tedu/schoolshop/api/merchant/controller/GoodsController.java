package cn.tedu.schoolshop.api.merchant.controller;

import cn.tedu.schoolshop.api.merchant.service.IGoodsService;
import cn.tedu.schoolshop.exception.service.InsertException;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.security.LoginMerchantInfo;
import cn.tedu.schoolshop.util.R;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 15:07
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin
@Slf4j
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 分页查询
     *
     * @param merchantInfo
     * @param category3Id  分类ID
     * @param pageNum      查询页
     * @return
     */
    @GetMapping("/{category3Id}/{pageNum}")
    public R goods(@AuthenticationPrincipal LoginMerchantInfo merchantInfo,
                   @PathVariable("category3Id") Integer category3Id,
                   @PathVariable("pageNum") Integer pageNum) {

        PageInfo<Goods> pageInfo =
                goodsService.findAllByMerchantId(pageNum, merchantInfo.getId(), category3Id);
        return R.ok(pageInfo);
    }

    /**
     * 添加商品
     *
     * @param merchantInfo
     * @param goods
     * @return
     */
    @PostMapping("")
    public R addGoods(@AuthenticationPrincipal LoginMerchantInfo merchantInfo,
                      @RequestBody Goods goods) {
        goods.setMerchantId(merchantInfo.getId())
                .setCategory1Id(merchantInfo.getCategory1Id())
                .setCategory2Id(merchantInfo.getCategory2Id());

        if (goodsService.save(goods)) {
            return R.ok("添加成功");
        } else {
            return R.failure(R.State.ERR_INSERT, new InsertException("添加商品失败"));
        }

    }

    /**
     * 修改商品
     *
     * @param merchantInfo
     * @param goods
     * @return
     */
    @PutMapping("")
    public R updateGoods(@AuthenticationPrincipal LoginMerchantInfo merchantInfo,
                         @RequestBody Goods goods) {
        log.debug("商家修改商品{}", goods);
        goods.setUpdateTime(LocalDateTime.now());

        //修改条件s
        UpdateWrapper<Goods> goodsUpdateWrapper = new UpdateWrapper<>();
        goodsUpdateWrapper
                .eq("id", goods.getId())
                .eq("merchant_id", merchantInfo.getId());
        if (goodsService.update(goods, goodsUpdateWrapper)) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UNKNOWN, new UpdateExteption("修改失败"));
        }
    }


}
