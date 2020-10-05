package cn.tedu.schoolshop.getway.service;


import cn.tedu.schoolshop.model.GoodsComment;
import cn.tedu.schoolshop.vo.goodsComment.GoodsCommentVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 货物评价 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-24
 */
public interface IGoodsCommentService extends IService<GoodsComment> {

    PageInfo<GoodsComment> findByGoodId(Integer goodId, Integer pageNum);
}
