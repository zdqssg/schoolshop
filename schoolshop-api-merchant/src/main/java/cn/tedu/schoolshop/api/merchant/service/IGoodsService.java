package cn.tedu.schoolshop.api.merchant.service;

import cn.tedu.schoolshop.model.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 商家货物 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
public interface IGoodsService extends IService<Goods> {
    PageInfo<Goods> findAllByMerchantId(Integer pageNum,Integer merchantId,Integer category3Id);
}
