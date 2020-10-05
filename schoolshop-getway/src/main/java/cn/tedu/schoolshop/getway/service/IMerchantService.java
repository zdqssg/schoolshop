package cn.tedu.schoolshop.getway.service;

import cn.tedu.schoolshop.model.Merchant;
import cn.tedu.schoolshop.vo.store.StoreVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下接口吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 10:29
 */
public interface IMerchantService extends IService<Merchant> {
    StoreVo findStoreById(Integer merchantId);
}

