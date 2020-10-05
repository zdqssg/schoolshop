package cn.tedu.schoolshop.getway.controller;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 用户，商家，管理员未登录的请求 }
 * @email zdq247209@163.com
 * @date 2020/9/15 19:41
 */
@Controller
public class NoneLoginController {
    //用户登录页面
    @RequestMapping("/person/none/login")
    public String userLogin(){
        return "login/user/login";
    }
    //用户注册页面
    @RequestMapping("/person/none/register")
    public String userRegister() {
        return "login/user/register";
    }
    //商家登录页面
    @RequestMapping("/merchant/none/login")
    public String merchantLogin() {
        return "login/merchant/login";
    }
    //商家注册页面
    @RequestMapping("/merchant/none/register")
    public String merchantRegister() {
        return "login/merchant/register";
    }
    //管理员登录页面
    @RequestMapping("/admin/none/login")
    public String adminLogin() {
        return "login/admin/login";
    }
}
