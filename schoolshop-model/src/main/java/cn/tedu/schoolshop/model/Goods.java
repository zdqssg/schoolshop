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
import java.util.List;

/**
 * <p>
 * 商家货物
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods")
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货物名称
     */
    @TableField("goods_name")
    private String goodsName;
    /**
     * 1级分类ID
     */
    @TableField("category1_id")
    private Integer category1Id;
    /**
     * 2级分类ID
     */
    @TableField("category2_id")
    private Integer category2Id;
    /**
     * 3级分类ID
     */
    @TableField("category3_id")
    private Integer category3Id;

    /**
     * 商品描述
     */
    @TableField("goods_describe")
    private String goodsDescribe;

    /**
     * 货物单价
     */
    @TableField("goods_price")
    private Integer goodsPrice;

    /**
     * 库存
     */
    @TableField("goods_repertory")
    private Integer goodsRepertory;

    /**
     * 货物封面
     */
    @TableField("goods_img_path")
    private String goodsImgPath;

    /**
     * 货物状态
     */
    @TableField("goods_state")
    private Integer goodsState;

    /**
     * 出售量
     */
    @TableField("goods_sale")
    private Integer goodsSale;

    /**
     * 浏览次数
     */
    @TableField("goods_click_count")
    private Integer goodsClickCount;

    /**
     * 收藏次数
     */
    @TableField("goods_like_count")
    private Integer goodsLikeCount;

    /**
     * 支付次数
     */
    @TableField("goods_pay_count")
    private Integer goodsPayCount;

    /**
     * 加入购物车次数
     */
    @TableField("goods_add_cart")
    private Integer goodsAddCart;

    /**
     * 是否打折
     */
    @TableField("is_discount")
    private Boolean isDiscount;

    /**
     * 优惠率
     */
    @TableField("discount")
    private Integer discount;

    /**
     * 好评率
     */
    @TableField("rate")
    private Integer rate;
    /**
     * 评级
     */
    @TableField("star_level")
    private Integer starLevel;

    /**
     * 商家ID
     */
    @TableField("merchant_id")
    private Integer merchantId;

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
