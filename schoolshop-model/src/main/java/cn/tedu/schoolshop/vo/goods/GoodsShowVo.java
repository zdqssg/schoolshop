package cn.tedu.schoolshop.vo.goods;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/14 12:48
 */
@Data
@Accessors(chain = true)
public class GoodsShowVo implements Serializable {
    private Integer id;
    private String goodsName;
    private Integer goodsState;
    private Integer goodsPrice;
    private String goodsDescribe;
    private String goodsImgPath;
    private Integer isDiscount;
    private Integer discount;
    private Integer rate;
    private Integer starLevel;
    private Integer merchantId;
}
