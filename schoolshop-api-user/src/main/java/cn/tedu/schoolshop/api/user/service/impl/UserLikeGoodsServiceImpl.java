package cn.tedu.schoolshop.api.user.service.impl;


import cn.tedu.schoolshop.api.user.mapper.UserLikeGoodsMapper;
import cn.tedu.schoolshop.api.user.service.IUserLikeGoodsService;
import cn.tedu.schoolshop.model.UserLikeGoods;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏商品 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-27
 */
@Service
public class UserLikeGoodsServiceImpl extends ServiceImpl<UserLikeGoodsMapper, UserLikeGoods> implements IUserLikeGoodsService {

    @Autowired
    private UserLikeGoodsMapper likeGoodsMapper;
    @Override
    public UserLikeGoods findByUserIdAndGoodId(UserLikeGoods likeGoods) {
        return likeGoodsMapper.findByUserIdAndGoodId(likeGoods) ;
    }
}
