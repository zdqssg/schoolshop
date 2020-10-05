package cn.tedu.schoolshop.getway.service;


import cn.tedu.schoolshop.vo.goods.GoodsShowVo;

import java.util.List;

/**
 * <p>
 * 头部推荐商品 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
public interface HeaderProductService{
    List<GoodsShowVo> selectAll();
}
