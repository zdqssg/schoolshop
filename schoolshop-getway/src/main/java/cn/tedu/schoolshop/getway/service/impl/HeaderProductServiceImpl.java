package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import cn.tedu.schoolshop.getway.mapper.HeaderProductMapper;
import cn.tedu.schoolshop.getway.service.HeaderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 头部推荐商品 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
@Service
public class HeaderProductServiceImpl  implements HeaderProductService {

    @Autowired
    private HeaderProductMapper productMapper;
    @Override
    public List<GoodsShowVo> selectAll() {
        return productMapper. selectAll();

    }
}
