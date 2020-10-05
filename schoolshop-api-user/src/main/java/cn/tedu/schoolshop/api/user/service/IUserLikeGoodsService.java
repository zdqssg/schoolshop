package cn.tedu.schoolshop.api.user.service;


import cn.tedu.schoolshop.model.UserLikeGoods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收藏商品 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-27
 */
public interface IUserLikeGoodsService extends IService<UserLikeGoods> {

    UserLikeGoods findByUserIdAndGoodId(UserLikeGoods likeGoods);
}
