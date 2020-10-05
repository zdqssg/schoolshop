package cn.tedu.schoolshop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("home_slide")
public class HomeSlide implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("goods_id")
    private Integer goodsId;
    /**
     * 状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 上标题
     */
    @TableField("title_top")
    private String titleTop;

    /**
     * 下标题
     */
    @TableField("bottom")
    private String bottom;

    /**
     * 按钮
     */
    @TableField("btn_value")
    private String btnValue;

    /**
     * 生成时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
