package cn.tedu.schoolshop.api.user.controller;


import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.api.user.dto.RegisterUserDTO;
import cn.tedu.schoolshop.exception.service.IllegalParameterException;
import cn.tedu.schoolshop.api.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 * /none 用户未登录状态下的允许的访问
 *
 * @author zdq247209@163.com
 * @since 2020-09-10
 */
@RestController
@RequestMapping("/none")
@Slf4j
public class UserNoneController {

    @Autowired
    private IUserService userService;

    /**
     * 注册功能
     * @param registerUserDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/reg")
    public R reg(@Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new IllegalParameterException(defaultMessage);
        }
        userService.registerUser(registerUserDTO);
        return R.ok("注册成功");
    }
}
