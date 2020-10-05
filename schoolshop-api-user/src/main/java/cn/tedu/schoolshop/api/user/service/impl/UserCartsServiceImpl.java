package cn.tedu.schoolshop.api.user.service.impl;

import cn.tedu.schoolshop.api.user.mapper.UserCartsMapper;
import cn.tedu.schoolshop.api.user.service.IUserCartsService;
import cn.tedu.schoolshop.model.UserCarts;
import cn.tedu.schoolshop.vo.CartDetailVo;
import cn.tedu.schoolshop.vo.HeaderUserCartVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户购物车 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
@Service
public class UserCartsServiceImpl extends ServiceImpl<UserCartsMapper, UserCarts> implements IUserCartsService {
    @Autowired
    private UserCartsMapper cartsMapper;

    @Override
    public List<HeaderUserCartVo> getHeaderCartByUserId(Integer id) {
        return cartsMapper.selectHeaderCartByUserId(id);
    }

    @Override
    public UserCarts findHasByUserIdAndGoodId(UserCarts carts) {
        return cartsMapper.findHasByUserIdAndGoodId(carts);
    }

    @Override
    public List<CartDetailVo> findAllByUserId(Integer id) {
        return cartsMapper.findUserId(new UserCarts().setUserId(id));
    }

    @Override
    public List<CartDetailVo> findAllByUserId(Integer id, String item) {
//         9,5,1,4
        String[] split = item.split(",");
        List<CartDetailVo> list = new ArrayList<>();
        for (String s : split) {
            List<CartDetailVo> cartDetailVo = cartsMapper.findUserId(
                    new UserCarts()
                            .setId(Integer.parseInt(s))
                            .setUserId(id)
                            .setState(1));
            list.add(cartDetailVo.get(0));
        }

        return list;
    }
}
