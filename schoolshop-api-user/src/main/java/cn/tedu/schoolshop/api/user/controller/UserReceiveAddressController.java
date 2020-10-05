package cn.tedu.schoolshop.api.user.controller;

import cn.tedu.schoolshop.api.user.service.IUserReceiveAddressService;
import cn.tedu.schoolshop.exception.service.NotFindException;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.UserReceiveAddress;
import cn.tedu.schoolshop.security.LoginUserInfo;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.userReceiveAddress.UserReceiveAddressVo;
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
 * @date 2020/9/28 17:34
 */
@RestController
@RequestMapping("/receiveAddress")
@CrossOrigin
@Slf4j
public class UserReceiveAddressController {
    @Autowired
    IUserReceiveAddressService addressService;

    @PostMapping("")
    public R addAdress(@AuthenticationPrincipal LoginUserInfo userInfo,
                       @RequestBody UserReceiveAddress receiveAddress) {
        log.debug("{}", receiveAddress);
        receiveAddress.setUserId(userInfo.getId());
        boolean b=false;
        if (receiveAddress.getId()==null){
             b = addressService.save(receiveAddress);
        }else {
             b = addressService.updateById(receiveAddress);
        }

        if (b) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UPDATE, new UpdateExteption("添加失败"));
        }

    }

    @GetMapping("")
    public R userReceiveAddress(@AuthenticationPrincipal LoginUserInfo userInfo) {
        List<UserReceiveAddressVo> addressVos = addressService.findByUserId(userInfo.getId());
        return R.ok(addressVos);
    }
    @GetMapping("/defaultAddr")
    public R defaultAddr(@AuthenticationPrincipal LoginUserInfo userInfo) {
        UserReceiveAddressVo addressVos = addressService.findDefaultAddrByUserId(userInfo.getId());
        if (addressVos==null){
            return R.failure(R.State.ERR_NOT_FIND,new NotFindException("没有设置默认地址"));
        }
        return R.ok(addressVos);
    }

    @DeleteMapping("/{id}")
    public R delete(@AuthenticationPrincipal LoginUserInfo userInfo,
                    @PathVariable("id") Integer id) {
        log.debug("正在删除{}", id);
        UserReceiveAddress address =
                new UserReceiveAddress()
                        .setId(id)
                        .setState(0)
                        .setUserId(userInfo.getId());
        if (addressService.updateById(address)) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UPDATE, new UpdateExteption("删除失败"));
        }

    }

    @PutMapping("/update")
    public R update(@AuthenticationPrincipal LoginUserInfo userInfo,
                    @RequestBody UserReceiveAddress receiveAddress) {
        receiveAddress.setUserId(userInfo.getId());
        if (addressService.updateDefault(receiveAddress)) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UPDATE, new UpdateExteption("修改失败"));
        }

    }

}
