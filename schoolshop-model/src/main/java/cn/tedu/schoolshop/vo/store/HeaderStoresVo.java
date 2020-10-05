package cn.tedu.schoolshop.vo.store;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/16 19:39
 */
@Data
public class HeaderStoresVo implements Serializable {
    private Integer merchantId;
    private String storeName;
}
