package cn.tedu.schoolshop.api.merchant.mapper;


import cn.tedu.schoolshop.model.Merchant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Repository
public interface MerchantMapper extends BaseMapper<Merchant> {
    Merchant selectByNickName(String nickname);
    Merchant selectByPhone(String phone);
    Merchant selectByStoreName(String storeName);
    Merchant selectClickCountAndSumMoneyById(Integer id);
}
