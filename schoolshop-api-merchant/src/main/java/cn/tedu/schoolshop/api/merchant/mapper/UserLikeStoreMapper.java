package cn.tedu.schoolshop.api.merchant.mapper;


import cn.tedu.schoolshop.model.UserLikeStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 收藏店铺 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Repository
public interface UserLikeStoreMapper extends BaseMapper<UserLikeStore> {
   int selectCountByMerchantId(Integer id);
}
