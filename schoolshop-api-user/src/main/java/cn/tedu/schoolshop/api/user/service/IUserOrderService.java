package cn.tedu.schoolshop.api.user.service;


import cn.tedu.schoolshop.model.UserOrder;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.order.UserOrderVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-29
 */
public interface IUserOrderService extends IService<UserOrder> {

    R addOrder(Integer id, String item);

    List<UserOrderVo> selectByUserId(UserOrder order);

    R toPay(UserOrderVo order);
}
