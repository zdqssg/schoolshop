package cn.tedu.schoolshop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;


    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 生日
     */
    @TableField("birth")
    private String birth;


    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 余额
     */
    @TableField("balance")
    private Integer balance;

    /**
     * 等级
     */
    @TableField("level")
    private Integer level;

    /**
     * 积分
     */
    @TableField("integral")
    private Integer integral;

    /**
     * 头像
     */
    @TableField("head_photo")
    private String headPhoto;
    /**
     * 启用
     */
    @TableField("is_enabled")
    private Integer isEnabled;
    /**
     * 锁定
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

    /**
     * 背景图片
     */
    @TableField("bg_img")
    private String bgImg;
    /**
     * 头部背景图片
     */
    @TableField("header_bg_img")
    private String headerBgImg;

}
