package cn.tedu.schoolshop.getway.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/12 16:10
 */
@Configuration
public class MultiHttpSecurityConfig {

    /**
     * 用户操作认证
     */
    @Configuration
    @Order(1)
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserDetailsServiceImpl userDetailsService;
        @Autowired
        private UserSavedRequestAwareAuthenticationSuccessHandler requestAwareAuthenticationSuccessHandler;


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            String userRequest = "/person/**";//用户的请求
            String userLoginHtml = "/person/none/login";// 自定义登录页面
            String userLoginRequestUrl = "/person/loginPost";//登陆界面发起登陆请求的URL
            String userLogoutUrl = "/person/goOut";//注销的请求
            //用户未登录允许的访问权限
            String[] webNotHasAuthority = {
                    "/person/none/**"

            };
            http
                    .antMatcher(userRequest)//用户的请求
                    .formLogin()
                    .loginPage(userLoginHtml)// 自定义登录页面
                    .loginProcessingUrl(userLoginRequestUrl)//登陆界面发起登陆请求的URL

                    .successHandler(requestAwareAuthenticationSuccessHandler)
                    .failureUrl(userLoginHtml)//登陆失败的页面跳转URL
                    .permitAll()
                    .and()
                    .rememberMe()//记住我的配置
                    .rememberMeParameter("rememberMe")
                    .tokenValiditySeconds(60 * 60 * 24)//有效期一天
                    .and()
                    .logout()
                    .logoutUrl(userLogoutUrl)//注销的请求
                    .deleteCookies("remove")//移除Cookie
                    .invalidateHttpSession(true)//移除Session
                    .logoutSuccessUrl(userLoginHtml)
                    .and()
                    .authorizeRequests()// 对请求授权
                    // error  127.0.0.1 将您重定向的次数过多
                    .antMatchers(webNotHasAuthority).permitAll()// 不需要身份认证的web
//                    .antMatchers("/api-user/**").hasRole("USER")//有角色才可以访问的api
                    .anyRequest().authenticated()//其他/person路径下的请求全部需要登陆
//                    .and()
//                    .headers().frameOptions().disable()//关闭X-Frame-Options
                    .and()
                    .csrf().disable();//关闭跨域的问题

        }
    }

    /**
     * 商家操作认证
     */
    @Configuration
    @Order(2)
    public static class MerchantConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private MerchantDetailsServiceImpl merchantDetailsService;


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(merchantDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            String userRequest = "/merchant/**";//用户的请求
            String userLoginHtml = "/merchant/none/login";// 自定义登录页面
            String userLoginRequestUrl = "/merchant/loginPost";//登陆界面发起登陆请求的URL
            String userLoginSuccessUrl = "/merchant/index";
            String userLogoutUrl = "/merchant/goOut";//注销的请求
            //用户未登录允许的访问权限
            String[] webNotHasAuthority = {
                    "/merchant/none/**"
            };
            http
                    .antMatcher(userRequest)//用户的请求
                    .formLogin()
                    .loginPage(userLoginHtml)// 自定义登录页面
                    .loginProcessingUrl(userLoginRequestUrl)//登陆界面发起登陆请求的URL
                    .defaultSuccessUrl(userLoginSuccessUrl, true)//登录成功的Url
                    .failureUrl(userLoginHtml)//登陆失败的页面跳转URL
                    .permitAll()
                    .and()
                    .rememberMe()//记住我的配置
                    .rememberMeParameter("rememberMe")
                    .tokenValiditySeconds(60 * 60 * 24)//有效期一天
                    .and()
                    .logout()
                    .logoutUrl(userLogoutUrl)//注销的请求
                    .deleteCookies("remove")//移除Cookie
                    .invalidateHttpSession(true)//移除Session
                    .logoutSuccessUrl(userLoginHtml)//注销后的页面
                    .and()
                    .authorizeRequests()// 对请求授权
                    // error  127.0.0.1 将您重定向的次数过多
                    .antMatchers(webNotHasAuthority).permitAll()// 不需要身份认证的web
                    .anyRequest().authenticated()//其他/person路径下的请求全部需要登陆
                    .and()
                    .headers().frameOptions().disable()//关闭X-Frame-Options
                    .and()
                    .csrf().disable();//关闭跨域的问题
        }
    }

    @Configuration
    @Order(3)
    public static class OtherSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            String[] staticResource = {

            };
            String[] accessWithoutLoginUrls = {
                    "/", "/index.html"
                    , "/home/**"
                    , "/store/**"            //店铺页面
                    , "/api-user/none/**"
                    , "/api-merchant/none/**"  //商家未登录允许的访问权限
                    , "/api-admin/none/**"     //管理员未登录允许的访问权限
                    , "/api-store/**"
                    , "/api-good/**"             //商品服务
                    , "/api-goodComment/**"        //商品评论
                    , "/api-advertising/**"            //商品广告服务
                    , "/api-search/**"              //搜索服务
                    , "/resource/**"           //资源服务器
                    , "/bootstrap3/**", "/css/**", "/images/**", "/img/**", "/JqeryKJ/**"
                    , "/js/**", "/rs-plugin/**", "/video/**", "/favicon.ico"};//允许访问Url
            http
                    .authorizeRequests()//启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护
                    .antMatchers(accessWithoutLoginUrls)//允许访问
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable();//关闭跨域的问题
        }
    }
}
