package cn.tedu.schoolshop.api.user.controller;

import cn.tedu.schoolshop.api.user.service.IGoodsCommentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/28 2:18
 */
@RestController
@RequestMapping("/userGoodsComment")
@CrossOrigin
@Slf4j

public class UserGoodsCommentController {
    @Autowired
    private IGoodsCommentService commentService;
}
