package cn.tedu.schoolshop.getway.mapper;


import cn.tedu.schoolshop.model.GoodsComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 货物评价 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-24
 */
@Repository
public interface GoodsCommentMapper extends BaseMapper<GoodsComment> {

    List<GoodsComment> selectByGoodId(Integer goodId);
}
