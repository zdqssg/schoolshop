package cn.tedu.schoolshop.api.merchant.service;

import cn.tedu.schoolshop.model.UserLikeStore;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收藏店铺 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
public interface IUserLikeStoreService extends IService<UserLikeStore> {
    int selectCountByMerchantId(Integer id);
}
