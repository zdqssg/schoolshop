package cn.tedu.schoolshop.getway.mapper;


import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 头部推荐商品 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
@Repository
public interface HeaderProductMapper{
    List<GoodsShowVo> selectAll();
}
