package cn.tedu.schoolshop.api.merchant.service.impl;

import cn.tedu.schoolshop.api.merchant.mapper.UserLikeStoreMapper;
import cn.tedu.schoolshop.api.merchant.service.IUserLikeStoreService;
import cn.tedu.schoolshop.model.UserLikeStore;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏店铺 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Service
public class UserLikeStoreServiceImpl extends ServiceImpl<UserLikeStoreMapper, UserLikeStore> implements IUserLikeStoreService {
@Autowired
private UserLikeStoreMapper likeStoreMapper;
    @Override
    public int selectCountByMerchantId(Integer id) {
        return likeStoreMapper.selectCountByMerchantId(id);
    }
}
