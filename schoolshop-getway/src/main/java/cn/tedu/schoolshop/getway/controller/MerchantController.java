package cn.tedu.schoolshop.getway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/14 14:16
 */
@Controller
@RequestMapping("/merchant")
@CrossOrigin
public class MerchantController {
    /**
     * 商家管理中心首页
     *
     * @return
     */
    @GetMapping("/index")
    public String merchantIndex() {
        return "merchant/index";
    }

    /**
     * 商家登录页面
     *
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "merchant/home/home";
    }

    @GetMapping("/goods")
    public String goodsList() {
        return "merchant/Goods/goods";
    }

    @GetMapping("/goods/{goodId}")
    public String goodDetails(@PathVariable("goodId") Integer goodId,
                              Model model) {
        model.addAttribute("goodId",goodId);
        return "merchant/Goods/goodDetails";
    }

    @GetMapping("/addGood")
    public String addGood() {
        return "merchant/Goods/addGood";
    }

    @GetMapping("/orderManage")
    public String orderManage() {
        return "merchant/order/orderManage";
    }

    @GetMapping("/info")
    public String info() {
        return "merchant/store/info";
    }

    @GetMapping("/slide")
    public String slide() {
        return "merchant/store/slide";
    }

    @GetMapping("/center")
    public String center() {
        return "merchant/shopkeeper/center";
    }

    @GetMapping("/homeSlide")
    public String homeSlide() {
        return "merchant/advertising/homeSlide";
    }

    @GetMapping("/homeStoreRecommend")
    public String homeStoreRecommend() {
        return "merchant/advertising/homeStoreRecommend";
    }

    @GetMapping("/homeGoodsRecommend")
    public String homeGoodsRecommend() {
        return "merchant/advertising/homeGoodsRecommend";
    }


}
