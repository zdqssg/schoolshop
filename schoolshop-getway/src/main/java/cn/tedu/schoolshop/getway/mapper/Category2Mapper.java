package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.vo.Category1Vo;
import cn.tedu.schoolshop.vo.Category2Vo;
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
public interface Category2Mapper {
   List<Category2Vo> selectAll();
   List<Category2Vo> selectByCategory1Id(Integer category1Id);
}
