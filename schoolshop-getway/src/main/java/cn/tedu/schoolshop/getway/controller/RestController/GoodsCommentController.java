package cn.tedu.schoolshop.getway.controller.RestController;

import cn.tedu.schoolshop.exception.service.UpdateExteption;
import cn.tedu.schoolshop.getway.service.IGoodsCommentService;
import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.model.GoodsComment;
import cn.tedu.schoolshop.util.R;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/24 2:51
 */
@RestController
@RequestMapping("/api-goodComment")
@CrossOrigin
@Slf4j
public class GoodsCommentController {
    @Autowired
    private IGoodsCommentService commentService;

    @GetMapping("/{goodId}/{pageNum}")
    public R getGoodCommentByGoodId(@PathVariable("goodId") Integer goodId,
                                    @PathVariable("pageNum") Integer pageNum) {
        PageInfo<GoodsComment> pageInfo = commentService.findByGoodId(goodId, pageNum);
        return R.ok(pageInfo);
    }

    @PutMapping("/addLike/{id}")
    public R updateGood(@PathVariable("id") Integer id) {
        GoodsComment comment = commentService.getById(id);
        Integer approval = comment.getApproval();
        boolean b = commentService.updateById(comment.setApproval(++approval));
        if (b){
            return R.ok(commentService.getById(id).getApproval());
        }else {
            return R.failure(R.State.ERR_UNKNOWN,new UpdateExteption("异常"));
        }
    }
}
