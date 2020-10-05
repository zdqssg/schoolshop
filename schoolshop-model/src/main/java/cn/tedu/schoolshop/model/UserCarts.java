package cn.tedu.schoolshop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户购物车
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_carts")
@Accessors(chain = true)
public class UserCarts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品数量
     */
    @TableField("number")
    private Integer number;


    /**
     * 购物车商品状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;


    /**
     * 货物id
     */
    @TableField("goods_id")
    private Integer goodsId;

    /**
     * 加入购物车时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
