package cn.tedu.schoolshop.api.merchant.service;

import cn.tedu.schoolshop.api.merchant.dto.RegisterMerchantDTO;
import cn.tedu.schoolshop.model.Merchant;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
public interface IMerchantService extends IService<Merchant> {

    void registerMerchant(RegisterMerchantDTO registerMerchantDTO);
    Merchant selectClickCountAndSumMoneyById(Integer id);
}
