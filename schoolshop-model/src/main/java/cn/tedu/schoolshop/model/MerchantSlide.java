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
 * 商家轮播图
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("merchant_slide")
@Accessors(chain = true)
public class MerchantSlide implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商家id
     */
    @TableField("merchant_id")
    private Integer merchantId;

    /**
     * 商品id
     */
    @TableField("good_id")
    private Integer goodId;

    /**
     * 店铺轮播图状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 主标题
     */
    @TableField("base_title")
    private String baseTitle;

    /**
     * 副标题
     */
    @TableField("second_title")
    private String secondTitle;

    /**
     * 按钮文本
     */
    @TableField("btn")
    private String btn;

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
