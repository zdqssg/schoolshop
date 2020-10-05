package cn.tedu.schoolshop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 地区设置
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bs_area")
public class BsArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增列
     */
    @TableId(value = "AREA_ID", type = IdType.AUTO)
    private Integer areaId;

    /**
     * 区代码
     */
    @TableField("AREA_CODE")
    private String areaCode;

    /**
     * 父级市代码
     */
    @TableField("CITY_CODE")
    private String cityCode;

    /**
     * 市名称
     */
    @TableField("AREA_NAME")
    private String areaName;

    /**
     * 简称
     */
    @TableField("SHORT_NAME")
    private String shortName;

    /**
     * 经度
     */
    @TableField("LNG")
    private String lng;

    /**
     * 纬度
     */
    @TableField("LAT")
    private String lat;

    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField("GMT_CREATE")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField("GMT_MODIFIED")
    private Date gmtModified;

    /**
     * 备注
     */
    @TableField("MEMO")
    private String memo;

    /**
     * 状态
     */
    @TableField("DATA_STATE")
    private Integer dataState;

    /**
     * 租户ID
     */
    @TableField("TENANT_CODE")
    private String tenantCode;


}
