package cn.tedu.schoolshop.getway.controller;

import cn.tedu.schoolshop.security.LoginUserInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 用户请求页面 }
 * @email zdq247209@163.com
 * @date 2020/9/15 19:41
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "person/person";
    }

    /**
     * 个人信息
     *
     * @return
     */
    @GetMapping("/info")
    public String info() {
        return "person/info/information";
    }

    /**
     * 安全设置
     * @return
     */
    @GetMapping("/safety")
    public String safety() {
        return "person/info/safety";
    }
    /**
     * 收货地址
     * @return
     */
    @GetMapping("/address")
    public String address() {
        return "person/info/address";
    }
    /**
     * 订单管理
     * @return
     */
    @GetMapping("/order")
    public String order() {
        return "person/order/order";
    }

    /**
     * 退款售后
     * @return
     */
    @GetMapping("/change")
    public String change() {
        return "person/order/change";
    }

    /**
     * 购物车
     *
     * @return
     */
    @GetMapping("/cart")
    public String cart() {
        return "person/order/cart";
    }

    /**
     * 结算页面
     *
     * @return
     */
    @GetMapping("/checkout/{oderId}")
    public String checkout() {
        return "person/order/checkout";
    }


    /**
     * 收藏
     *
     * @return
     */
    @GetMapping("/collection")
    public String collection() {
        return "person/other/collection";
    }
    /**
     * 评价
     *
     * @return
     */
    @GetMapping("/comment")
    public String comment() {
        return "person/other/comment";
    }
    /**
     * 消息
     *
     * @return
     */
    @GetMapping("/news")
    public String news() {
        return "person/other/news";
    }



}
