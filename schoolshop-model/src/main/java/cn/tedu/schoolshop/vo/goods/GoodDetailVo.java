package cn.tedu.schoolshop.vo.goods;

import cn.tedu.schoolshop.model.GoodsComment;
import cn.tedu.schoolshop.vo.goodsComment.GoodsCommentVo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/25 17:34
 */
@Data
@Accessors(chain = true)
public class GoodDetailVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String goodsName;
    private Integer category1Id;
    private Integer category2Id;
    private Integer category3Id;
    private String goodsDescribe;
    private Integer goodsPrice;
    private Integer goodsRepertory;
    private String goodsImgPath;
    private Integer goodsState;
    private Integer goodsSale;
    private Integer goodsClickCount;
    private Integer goodsLikeCount;
    private Integer goodsPayCount;
    private Integer goodsAddCart;
    private Boolean isDiscount;
    private Integer discount;
    private Integer rate;
    private Integer starLevel;
    private Integer merchantId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<GoodsCommentVo> comment;
}
