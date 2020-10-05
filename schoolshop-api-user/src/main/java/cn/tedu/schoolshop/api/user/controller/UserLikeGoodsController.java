package cn.tedu.schoolshop.api.user.controller;

import cn.tedu.schoolshop.api.user.service.IUserLikeGoodsService;
import cn.tedu.schoolshop.exception.service.InsertException;
import cn.tedu.schoolshop.exception.service.NotFindException;
import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.model.UserLikeGoods;
import cn.tedu.schoolshop.security.LoginUserInfo;
import cn.tedu.schoolshop.util.R;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/27 19:12
 */
@RestController
@RequestMapping("/likeGoods")
@CrossOrigin
@Slf4j
public class UserLikeGoodsController {
    @Autowired
    private IUserLikeGoodsService likeGoodsService;

    @GetMapping("/{goodId}")
    public R userIsLikeThis(@AuthenticationPrincipal LoginUserInfo userInfo,
                            @PathVariable("goodId") Integer goodId) {

        UserLikeGoods likeGoods = new UserLikeGoods()
                .setUserId(userInfo.getId())
                .setGoodsId(goodId);
        log.debug("{}",likeGoods);
        UserLikeGoods idAndGoodId = likeGoodsService.findByUserIdAndGoodId(likeGoods);
        if (idAndGoodId!=null){
            if (idAndGoodId.getState()==1){
                return R.ok(idAndGoodId);

            }
        }
        return R.failure(R.State.ERR_NOT_FIND, new NotFindException("未收藏"));
    }

    @PostMapping("/{goodId}")
    public R userLikeThis(@AuthenticationPrincipal LoginUserInfo userInfo,
                            @PathVariable("goodId") Integer goodId) {

        UserLikeGoods likeGoods = new UserLikeGoods()
                .setUserId(userInfo.getId())
                .setGoodsId(goodId);
        log.debug("{}",likeGoods);
        UserLikeGoods idAndGoodId = likeGoodsService.findByUserIdAndGoodId(likeGoods);
        if (idAndGoodId==null){
            if (likeGoodsService.save(likeGoods)){
                return R.ok();
            }else {
                return R.failure(R.State.ERR_INSERT,new InsertException("服务器异常"));
            }
        }else {
            Integer state = idAndGoodId.getState()==0?1:0;

            idAndGoodId.setState(state);
            log.debug("{}",idAndGoodId);
            if (likeGoodsService.updateById(idAndGoodId)){
                return R.ok();
            }else {
                return R.failure(R.State.ERR_UPDATE,new UpdateExteption("服务器异常"));
            }
        }

    }
}
