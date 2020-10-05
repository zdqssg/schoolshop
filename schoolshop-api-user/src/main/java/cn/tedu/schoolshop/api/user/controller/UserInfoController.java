package cn.tedu.schoolshop.api.user.controller;

import cn.tedu.schoolshop.api.user.service.IUserService;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.User;
import cn.tedu.schoolshop.security.LoginUserInfo;
import cn.tedu.schoolshop.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/17 9:24
 */
@RestController
@RequestMapping("/info")
@CrossOrigin
@Slf4j
public class UserInfoController {

    @Autowired
    private IUserService userService;


    @GetMapping("/name")
    public R name(@AuthenticationPrincipal LoginUserInfo userInfo) {
        String nickname = userInfo.getNickname();
        return R.ok(nickname);
    }

    @GetMapping("/info")
    public R info(@AuthenticationPrincipal LoginUserInfo userInfo) {
        return R.ok(userService.getById(userInfo.getId()));
    }

    @GetMapping("/bgImg")
    public R bgImg(@AuthenticationPrincipal LoginUserInfo userInfo) {
        return R.ok(userService.getById(userInfo.getId()).getBgImg());
    }

    @PutMapping("/headPhoto")
    public R updateHeadPhoto(@AuthenticationPrincipal LoginUserInfo userInfo,
                             @RequestBody User user) {
        log.debug("用户头像{}", user);
        user.setId(userInfo.getId());
        boolean update = userService.updateById(user);
        if (update) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UPDATE, new UpdateExteption());
        }
    }


    @PutMapping("/changeGender")
    public R changeGender(@AuthenticationPrincipal LoginUserInfo userInfo,
                          @RequestBody User user) {
        user.setId(userInfo.getId());

        if (userService.updateById(user)) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UNKNOWN, new UpdateExteption("修改性别失败"));
        }
    }

    @PutMapping("/updateBirth")
    public R updateBirth(@AuthenticationPrincipal LoginUserInfo userInfo,
                         @RequestBody User user) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse = dateFormat.parse(birth);
        user.setId(userInfo.getId());
        if (userService.updateById(user)) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UNKNOWN, new UpdateExteption("修改性别失败"));
        }
    }

}
