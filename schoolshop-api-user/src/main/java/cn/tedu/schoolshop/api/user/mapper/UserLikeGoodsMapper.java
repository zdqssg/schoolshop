package cn.tedu.schoolshop.api.user.mapper;


import cn.tedu.schoolshop.model.UserLikeGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 收藏商品 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-27
 */
@Repository
public interface UserLikeGoodsMapper extends BaseMapper<UserLikeGoods> {

    UserLikeGoods findByUserIdAndGoodId(UserLikeGoods likeGoods);
}
