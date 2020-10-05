
package cn.tedu.schoolshop.api.merchant.service.impl;

import cn.tedu.schoolshop.api.merchant.mapper.GoodsMapper;
import cn.tedu.schoolshop.api.merchant.service.IGoodsService;
import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.vo.goods.GoodsCategoryIdMerchantIdVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家货物 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Value("${project.goods.page-size}")
    private Integer pageSize;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo<Goods> findAllByMerchantId(Integer pageNum, Integer merchantId, Integer category3Id) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);

        List<Goods> goods = goodsMapper.findAllByMerchantId(new GoodsCategoryIdMerchantIdVo(category3Id, merchantId));
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        return pageInfo;
    }
}
