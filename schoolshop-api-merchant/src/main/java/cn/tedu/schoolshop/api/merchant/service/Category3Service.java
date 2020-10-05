package cn.tedu.schoolshop.api.merchant.service;
import cn.tedu.schoolshop.vo.Category3Vo;

import java.util.List;

/**
 * <p>
 * 店铺分类 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
public interface Category3Service {
    List<Category3Vo> selectAll();
    List<Category3Vo> selectByCategory2Id(Integer category2Id);
}
