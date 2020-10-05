package cn.tedu.schoolshop.getway.service;


import cn.tedu.schoolshop.model.GoodsAdvertising;
import cn.tedu.schoolshop.util.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
public interface IGoodsAdvertisingService extends IService<GoodsAdvertising> {

    R addAdvertising(Integer goodId);

    R getRandom();

    R selectOne(Integer advertisingID);

    R getAll(Integer pageNum);

    R getRandomByMerchantId(Integer merchantId);
}
