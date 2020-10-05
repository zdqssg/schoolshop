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
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_receive_address")
@Accessors(chain = true)
public class UserReceiveAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 收货人
     */
    @TableField("username")
    private String username;

    /**
     * 默认收货地址
     */
    @TableField("state")
    private Integer state;

    /**
     * 省
     */
    @TableField("province_code")
    private String provinceCode;

    /**
     * 城市
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
     * 描述
     */
    @TableField("detail")
    private String detail;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
