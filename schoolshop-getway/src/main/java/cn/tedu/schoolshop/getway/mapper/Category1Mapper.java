package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.vo.Category1Vo;
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
public interface Category1Mapper {
   List<Category1Vo> selectAll();
}
