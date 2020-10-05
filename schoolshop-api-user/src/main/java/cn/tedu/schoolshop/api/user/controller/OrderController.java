package cn.tedu.schoolshop.api.user.controller;

import cn.tedu.schoolshop.api.user.service.IUserOrderService;
import cn.tedu.schoolshop.exception.service.NotFindException;
import cn.tedu.schoolshop.model.UserOrder;
import cn.tedu.schoolshop.security.LoginUserInfo;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.order.UserOrderVo;
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
 * @date 2020/9/29 12:54
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
@Slf4j
public class OrderController {
    @Autowired
    private IUserOrderService orderService;

    /**
     * 添加订单
     *
     * @param userInfo
     * @param item
     * @return
     */
    @PostMapping("/addOrder/{item}")
    public R addOrder(@AuthenticationPrincipal LoginUserInfo userInfo,
                      @PathVariable("item") String item) {
        return orderService.addOrder(userInfo.getId(), item);
    }

    /**
     * 显示要支付订单
     *
     * @param userInfo
     * @param orderId
     * @return
     */
    @GetMapping("/toPay/{orderId}")
    public R toPayInfo(@AuthenticationPrincipal LoginUserInfo userInfo,
                       @PathVariable("orderId") Integer orderId) {
        UserOrder order = new UserOrder()
                .setId(orderId)
                .setUserId(userInfo.getId())
                .setState(1);
        List<UserOrderVo> orderVos = orderService.selectByUserId(order);
        if (orderVos.size() == 0) {
            return R.failure(R.State.ERR_NOT_FIND, new NotFindException("未找到"));
        } else {
            return R.ok(orderVos.get(0));
        }
    }

    /**
     * 支付订单
     *
     * @param userInfo
     * @return
     */
    @PostMapping("")
    public R toPay(@AuthenticationPrincipal LoginUserInfo userInfo,
                   @RequestBody UserOrderVo order) {
        return orderService.toPay(order.setUserId(userInfo.getId()));
    }


    /**
     * 得到所有的订单
     */

    @GetMapping("")
    public R getAllOrder(@AuthenticationPrincipal LoginUserInfo userInfo) {
        UserOrder order = new UserOrder()
                .setUserId(userInfo.getId());
        List<UserOrderVo> orderVos = orderService.selectByUserId(order);
        return R.ok(orderVos);
    }

    /**
     * 某个状态的订单
     */
    @GetMapping("/state/{state}")
    public R getAllOrder(@AuthenticationPrincipal LoginUserInfo userInfo,
                         @PathVariable("state") Integer state) {
        UserOrder order = new UserOrder()
                .setUserId(userInfo.getId())
                .setState(state);
        List<UserOrderVo> orderVos = orderService.selectByUserId(order);
        return R.ok(orderVos);
    }

}
