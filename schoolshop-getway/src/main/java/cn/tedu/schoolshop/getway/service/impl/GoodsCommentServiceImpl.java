package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.GoodsCommentMapper;
import cn.tedu.schoolshop.getway.service.IGoodsCommentService;
import cn.tedu.schoolshop.model.GoodsComment;
import cn.tedu.schoolshop.vo.goodsComment.GoodsCommentVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 货物评价 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-24
 */
@Service
public class GoodsCommentServiceImpl extends ServiceImpl<GoodsCommentMapper, GoodsComment> implements IGoodsCommentService {
    @Value("${project.goodsComment.page-size}")
    private Integer pageSize;
    @Autowired
    private GoodsCommentMapper commentMapper;

    /**
     * 分页查询
     * @param goodId
     * @param pageNum
     * @return
     */
    @Override
    public PageInfo<GoodsComment> findByGoodId(Integer goodId, Integer pageNum) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsComment> goodsCommentList = commentMapper.selectByGoodId(goodId);
        PageInfo<GoodsComment> pageInfo = new PageInfo<>(goodsCommentList);
        return pageInfo;
    }


}
