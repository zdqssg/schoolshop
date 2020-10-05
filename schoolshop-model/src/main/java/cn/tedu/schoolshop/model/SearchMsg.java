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
 * 搜索历史
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("search_msg")
public class SearchMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 0商品 1店铺
     */
    @TableField("type")
    private Integer type;

    /**
     * 搜索内容
     */
    @TableField("msg")
    private String msg;

    /**
     * 搜索次数
     */
    @TableField("search_count")
    private Integer searchCount;

    /**
     * 男生搜索次数
     */
    @TableField("male_search")
    private Integer maleSearch;

    /**
     * 女生搜索次数
     */
    @TableField("female_search")
    private Integer femaleSearch;

    /**
     * 搜索时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
