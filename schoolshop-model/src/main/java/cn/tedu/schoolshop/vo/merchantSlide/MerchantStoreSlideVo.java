package cn.tedu.schoolshop.vo.merchantSlide;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/26 14:54
 */
@Data
@Accessors(chain = true)
public class MerchantStoreSlideVo implements Serializable {
    private Integer goodId;
    private String goodsName;
    private String goodsPrice;
    private String goodsImgPath;
    private String baseTitle;
    private String secondTitle;
    private String btn;
}
