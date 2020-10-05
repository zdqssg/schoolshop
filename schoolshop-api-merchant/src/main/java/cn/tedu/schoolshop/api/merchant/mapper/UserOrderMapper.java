package cn.tedu.schoolshop.api.merchant.mapper;



import cn.tedu.schoolshop.model.UserOrder;
import cn.tedu.schoolshop.vo.order.UserOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Repository
public interface UserOrderMapper extends BaseMapper<UserOrder> {
    List<UserOrder> selectAllCount();
    UserOrderVo selectById(Integer orderId);

}
