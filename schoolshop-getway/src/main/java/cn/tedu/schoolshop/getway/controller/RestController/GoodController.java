package cn.tedu.schoolshop.getway.controller.RestController;

import cn.tedu.schoolshop.exception.service.GoodNotFindException;
import cn.tedu.schoolshop.getway.service.IGoodsService;
import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/25 15:33
 */
@RestController
@RequestMapping("/api-good")
@CrossOrigin
@Slf4j
public class GoodController {
    @Autowired
    private IGoodsService goodsService;

    /**
     * 商品详情
     */
    @GetMapping("/info/{id}")
    public R getInfo(@PathVariable("id") Integer id) {
        Goods good = goodsService.getById(id);
        if (good != null) {
            return R.ok(good);
        } else {
            return R.failure(R.State.ERR_GOOD_NOT_FIND, new GoodNotFindException("商品未找到"));
        }
    }

    /**
     * 获取商品详情  评论分页获取
     *
     * @param id
     * @param pageNum
     * @return
     */
    @GetMapping("/{id}/{pageNum}")
    public R good(@PathVariable("id") Integer id,
                  @PathVariable("pageNum") Integer pageNum) {
        log.debug("商品ID{}", id);
        Map map = goodsService.findGoodDetailById(id, pageNum);
        log.debug("{}", map);
        if (map != null) {
            return R.ok(map);
        } else {
            return R.failure(R.State.ERR_GOOD_NOT_FIND, new GoodNotFindException("商品不存在"));
        }

    }

    /**
     * 商家最热卖4件商品
     *
     * @param merchantId
     * @return
     */
    @GetMapping("/{merchantId}/hotGoods")
    public R getStoreHotGoods(@PathVariable("merchantId") Integer merchantId) {
        List<GoodsShowVo> goods = goodsService.findMerchantHotGoods(merchantId);
        if (goods.size() == 0) {
            R.failure(R.State.ERR_GOOD_NOT_FIND, new GoodNotFindException());
        }
        return R.ok(goods);
    }

    /**
     * 商家新上4件商品
     *
     * @param merchantId
     * @return
     */
    @GetMapping("/{merchantId}/newGoods")
    public R getStoreNewGoods(@PathVariable("merchantId") Integer merchantId) {
        List<GoodsShowVo> goods = goodsService.findMerchantNewGoods(merchantId);
        if (goods.size() == 0) {
            R.failure(R.State.ERR_GOOD_NOT_FIND, new GoodNotFindException());
        }
        return R.ok(goods);
    }

    @GetMapping("/newGood")
    public R getnewGood() {
        GoodsShowVo goodsShowVo = goodsService.findNewGood();
        return R.ok(goodsShowVo);
    }

}
