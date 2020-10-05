package cn.tedu.schoolshop.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/17 18:23
 */
@Data
public class HeaderUserCartVo implements Serializable {
    private Integer id;
    private Integer number;
    private String goodsName;
    private String goodsPrice;
    private String goodsImgPath;
}
