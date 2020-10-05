package cn.tedu.schoolshop.vo.home;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/30 18:13
 */
@Data
@Accessors(chain = true)
public class HomeSlideVo implements Serializable {
    private Integer id;
    private Integer goodsId;
    private String goodsName;
    private Integer goodsPrice;
    private String goodsImgPath;
    private Integer merchantId;


    private String titleTop;
    private String bottom;
    private String btnValue;
}
