package cn.tedu.schoolshop.api.user.service;

import cn.tedu.schoolshop.model.UserCarts;
import cn.tedu.schoolshop.vo.CartDetailVo;
import cn.tedu.schoolshop.vo.HeaderUserCartVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户购物车 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
public interface IUserCartsService extends IService<UserCarts> {

    List<HeaderUserCartVo> getHeaderCartByUserId(Integer id);

    UserCarts findHasByUserIdAndGoodId(UserCarts carts);

    List<CartDetailVo> findAllByUserId(Integer id);

    List<CartDetailVo> findAllByUserId(Integer id, String item);
}
