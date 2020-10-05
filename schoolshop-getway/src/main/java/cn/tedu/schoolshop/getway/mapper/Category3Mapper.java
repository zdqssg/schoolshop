package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.vo.Category1Vo;
import cn.tedu.schoolshop.vo.Category2Vo;
import cn.tedu.schoolshop.vo.Category3Vo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 店铺分类 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
@Repository
public interface Category3Mapper {
   List<Category3Vo> selectAll();
   List<Category3Vo> selectByCategory2Id(Integer category2Id);
}
