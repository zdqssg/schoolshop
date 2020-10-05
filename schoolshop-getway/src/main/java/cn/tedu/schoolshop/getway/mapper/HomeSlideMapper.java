package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.model.HomeSlide;
import cn.tedu.schoolshop.vo.home.HomeSlideVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
@Repository
public interface HomeSlideMapper extends BaseMapper<HomeSlide> {
  List<HomeSlideVo> selectShowAll();
}
