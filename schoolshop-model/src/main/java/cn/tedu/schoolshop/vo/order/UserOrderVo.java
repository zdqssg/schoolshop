package cn.tedu.schoolshop.vo.order;

import cn.tedu.schoolshop.vo.CartDetailVo;
import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/29 14:56
 */
@Data
@Accessors(chain = true)
public class UserOrderVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Integer id;

    private Integer state;
    private String username;
    private String phone;


    private String provinceName;
    private String provinceCode;

    private String cityName;
    private String cityCode;

    private String areaName;
    private String areaCode;

    private String streetName;
    private String streetCode;

    /**
     * 地址描述
     */
    private String detail;

    /**
     * 备注信息
     */
    private String userWarn;

    /**
     * 购物车Id
     */
    private String cartId;
    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 下单时间
     */
    private LocalDateTime createTime;
    private List<CartDetailVo> cart;
}
