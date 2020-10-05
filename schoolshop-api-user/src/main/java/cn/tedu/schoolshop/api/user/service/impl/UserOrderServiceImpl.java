package cn.tedu.schoolshop.api.user.service.impl;


import cn.tedu.schoolshop.api.user.mapper.*;
import cn.tedu.schoolshop.api.user.service.IUserOrderService;
import cn.tedu.schoolshop.exception.service.*;
import cn.tedu.schoolshop.model.*;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.CartDetailVo;
import cn.tedu.schoolshop.vo.order.UserOrderVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-29
 */
@Service
@Slf4j
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private UserCartsMapper userCartsMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public R addOrder(Integer userId, String item) {
        String[] split = item.split(",");

        //修改购物车商品 状态为2表示购物车商品已为待支付
        for (String cartId : split) {
            UserCarts userCarts = new UserCarts()
                    .setId(Integer.parseInt(cartId))
                    .setUserId(userId)
                    .setState(2);
            userCartsMapper.updateById(userCarts);
        }
        UserOrder order = new UserOrder()
                .setUserId(userId)
                .setCartId(item);
        Integer inset = userOrderMapper.insertOrder(order);
        if (inset == 1) {
            return R.ok(order.getId());
        } else {
            return R.failure(R.State.ERR_INSERT, new InsertException("加入订单失败"));
        }

    }

    @Override
    public List<UserOrderVo> selectByUserId(UserOrder order) {
        log.debug("dto订单条件{}",order);
        List<UserOrderVo> orderVos = userOrderMapper.selectByUserId(order);
        for (UserOrderVo vo : orderVos) {
            List<CartDetailVo> cartDetailVos = getOrderGood(vo);
            vo.setCart(cartDetailVos);
        }
        return orderVos;
    }


    /**
     * 处理用户支付订单
     * @param order
     * @return
     */
    @Override
    public R toPay(UserOrderVo order) {
        log.debug("这个UserOrderVo{}", order);
        //查询订单状态
        List<UserOrderVo> orderVos = userOrderMapper.selectByUserId(new UserOrder().setId(order.getId()).setUserId(order.getUserId()));

        if (orderVos.size() == 0) {
            return R.failure(R.State.ERR_ORDER_NOT_FIND, new OrderNotFindException("订单未找到"));
        }
        UserOrderVo vo = orderVos.get(0);
        if (vo.getState() == 0) {
            return R.failure(R.State.ERR_ORDER_IS_DELETE, new OrderIsDeleteException("订单已删除"));
        }
        if (vo.getState() == 2) {
            return R.failure(R.State.ERR_ORDER_IS_PAY, new OrderIsDeleteException("订单已支付"));
        }

        //获取订单的商品详情
        List<CartDetailVo> orderGood = getOrderGood(order);
        if (orderGood == null || orderGood.size() == 0) {
            return R.failure(R.State.ERR_ORDER_EXCEPTION, new NotFindException("订单异常"));
        }

        //计算价格
        Integer sum = 0;
        for (CartDetailVo cartDetailVo : orderGood) {
            sum += cartDetailVo.getNumber() * cartDetailVo.getGood().getGoodsPrice();
        }

        //查询用户
        User user = userMapper.selectById(order.getUserId());
        Integer balance = user.getBalance();

        //判断金额是否充足
        if (balance < sum) {
            return R.failure(R.State.ERR_MONEY_NOT_ENOUGH, new MoneyNotEhoughException("金额不足，请先充值"));
        }

        //用户扣金额
        userMapper.updateById(user.setBalance(balance - sum));

        //操作
        for (CartDetailVo cartDetailVo : orderGood) {
            //商品减库存
            Goods goods = goodsMapper.selectById(cartDetailVo.getGood().getId());
            Integer repertory = goods.getGoodsRepertory();
            Integer payCount = goods.getGoodsPayCount();
            Integer sale = goods.getGoodsSale();
            goods.setGoodsRepertory(repertory - cartDetailVo.getNumber());
            goods.setGoodsPayCount(payCount + 1);
            goods.setGoodsSale(sale + cartDetailVo.getNumber());
            goodsMapper.updateById(goods);

            //
            Integer price = cartDetailVo.getNumber() * cartDetailVo.getGood().getGoodsPrice();
            Merchant merchant = merchantMapper.selectById(cartDetailVo.getGood().getMerchantId());
            Integer money = merchant.getMoney();
            Integer sumMoney = merchant.getSumMoney();
            Integer growthValue = merchant.getGrowthValue();
            Integer vol = merchant.getVol();

            merchant.setMoney(money + price);
            merchant.setSumMoney(sumMoney + price);
            merchant.setGrowthValue(growthValue + price);
            merchant.setVol(vol + cartDetailVo.getNumber());
            Integer level = merchantShouldUpgrade(merchant);
            if (level != null) {
                merchant.setLevel(level);
            }

            merchantMapper.updateById(merchant);
        }


        //修改订单状态
        int update = userOrderMapper.updateById(
                new UserOrder()
                        .setId(order.getId())
                        .setState(2)
                        .setProvinceCode(order.getProvinceCode())
                        .setCityCode(order.getCityCode())
                        .setAreaCode(order.getAreaCode())
                        .setStreetCode(order.getStreetCode())
                        .setDetail(order.getDetail())
                        .setUsername(order.getUsername())
                        .setPhone(order.getPhone())
                        .setUserWarn(order.getUserWarn())
        );
        if (update != 1) {
            return R.failure(R.State.ERR_UPDATE, new UpdateExteption("服务器异常"));
        }


        return R.ok();
    }


    /**
     * 得到订单的商品列表
     *
     * @return
     */
    private List<CartDetailVo> getOrderGood(UserOrderVo orderVo) {

        String[] cartIdArr = orderVo.getCartId().split(",");
        List<CartDetailVo> list = new ArrayList<>();
        for (String c : cartIdArr) {
            UserCarts userCarts = new UserCarts()
                    .setId(Integer.parseInt(c))
                    .setUserId(orderVo.getUserId())
                    .setState(2);
            List<CartDetailVo> cartDetailVo = userCartsMapper.findUserId(userCarts);
            if (cartDetailVo==null||cartDetailVo.size()==0){
                continue;
            }
            list.add(cartDetailVo.get(0));
        }
        if (list.size() != cartIdArr.length) {
            return null;
        }
        return list;
    }


    /**
     * 判断商家是否升级
     *
     * @param merchant
     * @return
     */
    private Integer merchantShouldUpgrade(Merchant merchant) {
        Integer value = merchant.getGrowthValue();
        Integer nowLevel = merchant.getLevel();
        Integer level = null;
        if (value <= 0) {    //1级
            level = 1;
        } else if (value < 100) {//2级
            level = 2;
        } else if (value < 500) {//3级
            level = 3;
        } else if (value < 2000) {//4级
            level = 4;
        } else if (value < 5000) {//5级
            level = 5;
        } else if (value < 10000) {//6级
            level = 6;
        } else if (value < 20000) {//7级
            level = 7;
        } else if (value < 50000) {//8级
            level = 8;
        } else if (value < 100000) {//9级
            level = 9;
        } else if (value < 500000) {//10级
            level = 10;
        } else if (value < 1000000) {//11级
            level = 11;
        } else if (value < 5000000) {//12级
            level = 12;
        } else if (value < 10000000) {//13级
            level = 13;
        } else if (value < 50000000) {//14级
            level = 14;
        } else if (value < 100000000) {//15级
            level = 15;
        } else {//16级
            level = 16;
        }
        if (level == nowLevel) {
            return null;
        } else {
            return level;
        }

    }
}
