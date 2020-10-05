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
 * @email zdq247209@163.com
 * @date 2020/9/12 11:45
 */
@Controller
@RequestMapping("/home")
public class IndexController {
    /**
     * 校园购首页
     *
     * @return
     */
    @GetMapping("")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request, response);
        return "home/home";
    }


    /**
     * 按关键字搜索商品
     *
     * @return
     */
    @GetMapping("/searchMsg/{msg}")
    public String search(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request, response);
        return "home/shopWord";
    }


    /**
     * 按类型搜索商品
     *
     * @return
     */
    @GetMapping("/searchCategory/{category}")
    public String searchByType(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request, response);
        return "home/shopWord";
    }

    /**
     * 商品帖子中心
     *
     * @return
     */
    @GetMapping("/advertising")
    public String advertising(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request, response);
        return "home/advertising";
    }
    /**
     * 商品帖子中心  首条显示 advertisingId
     *
     * @return
     */
    @GetMapping("/advertising/{advertisingId}")
    public String advertisingOne(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request, response);
        return "home/advertising";
    }
    /**
     * 反馈建议
     *
     * @return
     */
    @GetMapping("/contact")
    public String contact(HttpServletRequest request, HttpServletResponse response) {
        //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request, response);
        return "home/contact";
    }

    /**
     * 关于我们
     *
     * @return
     */
    @GetMapping("/aboutUs")
    public String aboutUs(HttpServletRequest request, HttpServletResponse response) {
       //记住登录前的页面url
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request, response);
        return "home/aboutus";
    }


}
