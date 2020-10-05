package cn.tedu.schoolshop.api.user.service.impl;


import cn.tedu.schoolshop.api.user.mapper.GoodsCommentMapper;
import cn.tedu.schoolshop.api.user.service.IGoodsCommentService;
import cn.tedu.schoolshop.model.GoodsComment;
import cn.tedu.schoolshop.vo.goodsComment.UserGoodsCommentVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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

    @Autowired
    private GoodsCommentMapper commentMapper;



}
