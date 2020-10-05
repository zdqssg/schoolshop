package cn.tedu.schoolshop.api.user.mapper;

import cn.tedu.schoolshop.model.UserCarts;
import cn.tedu.schoolshop.vo.CartDetailVo;
import cn.tedu.schoolshop.vo.HeaderUserCartVo;
import cn.tedu.schoolshop.vo.order.UserOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户购物车 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
@Repository
public interface UserCartsMapper extends BaseMapper<UserCarts> {
    List<HeaderUserCartVo> selectHeaderCartByUserId(Integer id);

    UserCarts findHasByUserIdAndGoodId(UserCarts carts);

    List<CartDetailVo> findUserId(UserCarts carts);

}
