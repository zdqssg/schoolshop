package cn.tedu.schoolshop.api.merchant.service.impl;


import cn.tedu.schoolshop.api.merchant.mapper.UserCartsMapper;
import cn.tedu.schoolshop.api.merchant.mapper.UserOrderMapper;
import cn.tedu.schoolshop.api.merchant.service.IUserOrderService;
import cn.tedu.schoolshop.model.UserCarts;
import cn.tedu.schoolshop.model.UserOrder;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.CartDetailVo;
import cn.tedu.schoolshop.vo.order.UserOrderVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {
    @Value("${project.order.page-size}")
    private Integer pageSize;

    @Autowired
    private UserOrderMapper orderMapper;

    @Autowired
    UserCartsMapper cartsMapper;

    @Override
    public int selectCountByMerchantId(Integer id) {
        List<UserOrder> orders = orderMapper.selectAllCount();
        Integer count = 0;
        for (UserOrder order : orders) {
            List<CartDetailVo> good = getOrderGood(order);
            for (CartDetailVo detailVo : good) {
                if (detailVo.getGood().getMerchantId().equals(id)) {
                    count++;
                }
                ;
            }
        }
        return count;
    }

    @Override
    public R findAll(Integer id, Integer pageNum) {
//        if (pageNum == null || pageNum < 1) {
//            pageNum = 1;
//        }
//        起初订单的数据库设计有问题 暂时不使用PageHelper这里

//        PageHelper.startPage(pageNum, pageSize);
        //查询所有的订单
        List<UserOrder> orders = orderMapper.selectAllCount();

        List<UserOrderVo> list = new ArrayList<>();
        for (UserOrder order : orders) {
            if (order.getState()==0){
                break;
            }
            //得到订单中的商品集合
            List<CartDetailVo> good = getOrderGood(order);
            List<CartDetailVo> arrayList = new ArrayList<>();
            for (CartDetailVo detailVo : good) {
                 //判断商品是否为商家的
                if (detailVo.getGood().getMerchantId().equals(id)) {
                    arrayList.add(detailVo);
                }
            }

            UserOrderVo vo = orderMapper.selectById(order.getId());
            vo.setCart(arrayList);
            list.add(vo);
        }


        return R.ok(list);
    }

    /**
     * 得到订单的商品列表
     *
     * @return
     */
    private List<CartDetailVo> getOrderGood(UserOrder orderVo) {

        String[] cartIdArr = orderVo.getCartId().split(",");
        List<CartDetailVo> list = new ArrayList<>();
        for (String c : cartIdArr) {
            UserCarts userCarts = new UserCarts()
                    .setId(Integer.parseInt(c))
                    .setUserId(orderVo.getUserId());
            List<CartDetailVo> cartDetailVo = cartsMapper.findUserId(userCarts);
            list.add(cartDetailVo.get(0));
        }
        if (list.size() != cartIdArr.length) {
            return null;
        }
        return list;
    }
}
