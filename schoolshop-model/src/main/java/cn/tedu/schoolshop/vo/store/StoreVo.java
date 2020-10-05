package cn.tedu.schoolshop.vo.store;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/26 10:39
 */
@Data
@Accessors(chain = true)
public class StoreVo implements Serializable {
    private Integer id;
    private String storeName;
    private String storeHeadPhoto;
    private Integer category1Id;
    private Integer category2Id;

}
