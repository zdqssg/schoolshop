package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.model.Merchant;
import cn.tedu.schoolshop.vo.store.StoreVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下接口吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 10:29
 */
@Repository
public interface MerchantMapper extends BaseMapper<Merchant> {

    StoreVo findStoreById(Integer merchantId);
}

