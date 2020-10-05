package cn.tedu.schoolshop.vo.GoodsAdvertising;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/1 0:30
 */
@Data
@Accessors(chain = true)
public class GoodsAdvertisingVo implements Serializable {
    private Integer id;
    private Integer goodId;
    private String goodsName;
    private String goodsDescribe;
    private String goodsImgPath;
    private Integer merchantId;
    private String storeName;
    private String category3Name;
    private LocalDateTime createTime;

}
