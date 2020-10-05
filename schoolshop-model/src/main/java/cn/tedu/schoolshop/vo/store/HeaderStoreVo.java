package cn.tedu.schoolshop.vo.store;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/14 12:50
 */
@Data
@Accessors(chain = true)
public class HeaderStoreVo implements Serializable {
    private Integer merchantId;
    private String storeName;
    private String showImg;
    private String showMessageTop;
    private String showMessageBottom;
    private String showTag;
}
