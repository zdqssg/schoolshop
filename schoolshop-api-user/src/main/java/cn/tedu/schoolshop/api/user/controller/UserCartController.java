package cn.tedu.schoolshop.api.user.controller;

import cn.tedu.schoolshop.api.user.service.IUserCartsService;
import cn.tedu.schoolshop.exception.service.CartDuplicateException;
import cn.tedu.schoolshop.exception.service.CartInsertException;
import cn.tedu.schoolshop.exception.service.NotFindException;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.UserCarts;
import cn.tedu.schoolshop.security.LoginUserInfo;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.CartDetailVo;
import cn.tedu.schoolshop.vo.HeaderUserCartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/17 18:16
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin
@Slf4j

public class UserCartController {
    @Autowired
    private IUserCartsService cartsService;

    /**
     * 头部购物车信息
     *
     * @return
     */
    @GetMapping("/headerCart")
    public R headerCart(@AuthenticationPrincipal LoginUserInfo userInfo) {
        List<HeaderUserCartVo> userCartVos = cartsService.getHeaderCartByUserId(userInfo.getId());
        System.out.println(userCartVos);
        return R.ok(userCartVos);
    }

    /**
     * 添加商品
     *
     * @param userInfo
     * @param goodId
     * @return
     */
    @PostMapping("/{goodId}")
    public R addCart(@AuthenticationPrincipal LoginUserInfo userInfo,
                     @PathVariable("goodId") Integer goodId) {
        UserCarts carts = new UserCarts()
                .setGoodsId(goodId)
                .setUserId(userInfo.getId());
        if (cartsService.findHasByUserIdAndGoodId(carts) != null) {
            return R.failure(R.State.ERR_CART_DUPLICATE, new CartDuplicateException("购物车已存在"));
        }
        if (!cartsService.save(carts)) {
            return R.failure(R.State.ERR_CART_INSERT, new CartInsertException("添加购物车失败！"));
        }
        return R.ok();
    }

    /**
     * 获取购物车
     */
    @GetMapping("")
    public R cart(@AuthenticationPrincipal LoginUserInfo userInfo) {
        List<CartDetailVo> cart = cartsService.findAllByUserId(userInfo.getId());
        return R.ok(cart);
    }
    /**
     * 获取购物车
     */
    @GetMapping("/pay/{item}")
    public R cartToPay(@AuthenticationPrincipal LoginUserInfo userInfo,
                      @PathVariable("item") String item) {
        log.debug("{}",item);
        List<CartDetailVo> cart = cartsService.findAllByUserId(userInfo.getId(),item);
        if (cart.size()==0){
            return R.failure(R.State.ERR_NOT_FIND,new NotFindException("系统找不到"));
        }
        return R.ok(cart);
    }

    /**
     * 删除购物车
     */
    @DeleteMapping("/{id}")
    public R delete(@AuthenticationPrincipal LoginUserInfo userInfo,
                    @PathVariable("id") Integer id) {
        UserCarts carts = new UserCarts()
                .setId(id)
                .setUserId(userInfo.getId())
                .setState(0)
                .setUpdateTime(LocalDateTime.now());
        boolean update = cartsService.updateById(carts);
        if (update) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UPDATE, new UpdateExteption("删除失败"));
        }

    }
    /**
     * 修改数量
     */
    @PutMapping("")
    public R updateNumber(@RequestBody UserCarts carts){
        log.debug("UserCarts{}",carts);
        if (carts.getNumber()<1){
            return R.failure(R.State.ERR_UPDATE,new UpdateExteption("无法删减"));
        }
        carts.setUpdateTime(LocalDateTime.now());

        boolean update = cartsService.updateById(carts);
        if (update){
            return R.ok();
        }else {
            return R.failure(R.State.ERR_UPDATE,new UpdateExteption("服务器异常"));
        }
    }
}
