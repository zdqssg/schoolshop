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
 * @since 2020-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("merchant")
@Accessors(chain = true)
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 商家密码
     */
    @TableField("password")
    private String password;

    /**
     * 商家手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 一级分类ID
     */
    @TableField("category1_id")
    private Integer category1Id;

    /**
     * 二级分类ID
     */
    @TableField("category2_id")
    private Integer category2Id;

    /**
     * 店铺名
     */
    @TableField("store_name")
    private String storeName;

    /**
     * 店铺的开店状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 商家认证姓名
     */
    @TableField("username")
    private String username;

    /**
     * 店铺头像
     */
    @TableField("store_head_photo")
    private String storeHeadPhoto;

    /**
     * 店铺浏览量
     */
    @TableField("click_count")
    private Integer clickCount;

    /**
     * 商家金额
     */
    @TableField("money")
    private Integer money;

    /**
     * 总收入
     */
    @TableField("sum_money")
    private Integer sumMoney;

    /**
     * 商家等级
     */
    @TableField("level")
    private Integer level;

    /**
     * 成长值
     */
    @TableField("growth_value")
    private Integer growthValue;

    /**
     * 交易量
     */
    @TableField("VOL")
    private Integer vol;

    /**
     * 是否启用
     */
    @TableField("is_enabled")
    private Integer isEnabled;

    /**
     * 是否锁定
     */
    @TableField("is_locked")
    private Integer isLocked;

    /**
     * 注册时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
