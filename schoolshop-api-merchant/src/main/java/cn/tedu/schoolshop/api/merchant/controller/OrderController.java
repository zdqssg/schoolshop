package cn.tedu.schoolshop.api.merchant.controller;

import cn.tedu.schoolshop.api.merchant.service.IUserOrderService;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.UserOrder;
import cn.tedu.schoolshop.security.LoginMerchantInfo;
import cn.tedu.schoolshop.util.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/30 0:31
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IUserOrderService orderService;

    @GetMapping("/orderNumber")
    public R orderNumber(@AuthenticationPrincipal LoginMerchantInfo merchantInfo) {
        int i = orderService.selectCountByMerchantId(merchantInfo.getId());
        return R.ok(i);
    }

    @GetMapping("/{pageNum}")
    public R orderNumber(@AuthenticationPrincipal LoginMerchantInfo merchantInfo,
                         @PathVariable("pageNum") Integer pageNum) {
        return orderService.findAll(merchantInfo.getId(),pageNum);
    }

    /**
     * 发货
     */
    @PutMapping("/sendGood/{orderId}")
    public R sendGood(@PathVariable("orderId")Integer orderId){
        boolean update = orderService.updateById(new UserOrder().setId(orderId).setState(3));
        if (update){
            return R.ok();
        }else {
            return R.failure(R.State.ERR_UPDATE,new UpdateExteption("系统异常"));
        }
    }
}
