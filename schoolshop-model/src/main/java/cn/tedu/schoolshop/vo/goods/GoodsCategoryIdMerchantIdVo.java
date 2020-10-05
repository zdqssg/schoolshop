package cn.tedu.schoolshop.vo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategoryIdMerchantIdVo implements Serializable {

    private Integer category3Id;
    private Integer merchantId;
}
