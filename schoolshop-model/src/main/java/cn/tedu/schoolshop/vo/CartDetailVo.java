package cn.tedu.schoolshop.vo;

import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/28 9:13
 */
@Data
@Accessors(chain = true)
public class CartDetailVo implements Serializable {

    private Integer id;

    private Integer number;

    private LocalDateTime createTime;

    private GoodsShowVo good;
}
