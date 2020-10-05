package cn.tedu.schoolshop.api.merchant.controller;

import cn.tedu.schoolshop.api.merchant.dto.RegisterMerchantDTO;
import cn.tedu.schoolshop.api.merchant.service.IMerchantService;
import cn.tedu.schoolshop.exception.service.IllegalParameterException;
import cn.tedu.schoolshop.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 3:03
 */
@RestController
@RequestMapping("/none")
@Slf4j
public class MerchantNoneController {

    @Autowired
    private IMerchantService merchantService;
    /**
     * 注册功能
     * @param registerMerchantDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/reg")
    public R reg(@Valid RegisterMerchantDTO registerMerchantDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new IllegalParameterException(defaultMessage);
        }
        log.debug("商家注册》》》"+registerMerchantDTO);
        merchantService.registerMerchant(registerMerchantDTO);
        return R.ok("注册成功");
    }

}
