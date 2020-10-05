package cn.tedu.schoolshop.api.merchant.service;


import cn.tedu.schoolshop.model.UserOrder;
import cn.tedu.schoolshop.util.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
public interface IUserOrderService extends IService<UserOrder> {
    int selectCountByMerchantId(Integer id);

    R findAll(Integer id, Integer pageNum);
}
