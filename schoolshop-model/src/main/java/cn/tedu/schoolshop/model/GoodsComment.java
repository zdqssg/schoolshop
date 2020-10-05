package cn.tedu.schoolshop.model;

import cn.tedu.schoolshop.vo.user.UserCommendVo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 货物评价
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_comment")
@Accessors(chain = true)
public class GoodsComment implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评级
     * 1-5
     */
    @TableField("level")
    private Integer level;

    /**
     * 评语
     */
    @TableField("comment")
    private String comment;

    /**
     * 点赞量
     */
    @TableField("approval")
    private Integer approval;
    /**
     * 0  删除
     * 1  匿名
     * 2  显示用户名
     */
    @TableField("state")
    private Integer state;

    /**
     * 商品Id
     */
    @TableField("goods_id")
    private Integer goodsId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 评论时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
