package cn.tedu.schoolshop.getway.service;
import cn.tedu.schoolshop.vo.Category1Vo;
import cn.tedu.schoolshop.vo.Category2Vo;

import java.util.List;

/**
 * <p>
 * 店铺分类 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
public interface Category2Service {
    List<Category2Vo> selectAll();
    List<Category2Vo> selectByCategory1Id(Integer category1Id);
}
