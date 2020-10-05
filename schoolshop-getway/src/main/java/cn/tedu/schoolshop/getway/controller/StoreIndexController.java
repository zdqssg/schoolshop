package cn.tedu.schoolshop.getway.controller;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/26 10:01
 */
@Controller
@RequestMapping("/store")
public class StoreIndexController {


    /**
     * 店铺详情
     *
     * @return
     */
    @GetMapping("/{merchantId}")
    public String store(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request,response);
        return "home/store/store";
    }
    /**
     * 商品详情
     *
     * @return
     */
    @GetMapping("/{merchantId}/good/{id}")
    public String product(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request,response);
        return "home/store/productdetail";
    }

    /**
     * 商品列表
     *
     * @return
     */
    @GetMapping("/{merchantId}/goods/{category3Id}")
    public String storeProductList(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request,response);
        return "home/store/storeProductList";
    }

}
