package cn.tedu.schoolshop.getway.service.impl;

import cn.tedu.schoolshop.getway.mapper.MerchantMapper;
import cn.tedu.schoolshop.getway.service.IMerchantService;
import cn.tedu.schoolshop.model.Merchant;
import cn.tedu.schoolshop.vo.store.StoreVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 10:29
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {
    @Autowired
    private MerchantMapper merchantMapper;
    @Override
    public StoreVo findStoreById(Integer merchantId) {
        return merchantMapper.findStoreById(merchantId);
    }
}

