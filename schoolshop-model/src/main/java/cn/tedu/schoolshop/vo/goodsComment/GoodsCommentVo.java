package cn.tedu.schoolshop.vo.goodsComment;

import cn.tedu.schoolshop.vo.user.UserCommendVo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/25 17:47
 */
@Data
@Accessors(chain = true)
public class GoodsCommentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 评级
     * 1-5
     */
    private Integer level;

    /**
     * 评语
     */
    private String comment;

    /**
     * 点赞量
     */
    private Integer approval;

    private LocalDateTime createTime;
    private UserCommendVo user;
}
