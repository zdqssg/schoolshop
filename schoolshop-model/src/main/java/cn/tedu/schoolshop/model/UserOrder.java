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
 * 
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_order")
@Accessors(chain = true)
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单状态
     */
    @TableField("state")
    private Integer state;
    /**
     * 收货人
     */
    @TableField("username")
    private String username;
    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 省
     */
    @TableField("province_code")
    private String provinceCode;

    /**
     * 市
     */
    @TableField("city_code")
    private String cityCode;

    /**
     * 区
     */
    @TableField("area_code")
    private String areaCode;

    /**
     * 街道
     */
    @TableField("street_code")
    private String streetCode;

    /**
     * 地址描述
     */
    @TableField("detail")
    private String detail;

    /**
     * 备注信息
     */
    @TableField("user_warn")
    private String userWarn;

    /**
     * 购物车Id
     */
    @TableField("cart_id")
    private String cartId;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 下单时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
