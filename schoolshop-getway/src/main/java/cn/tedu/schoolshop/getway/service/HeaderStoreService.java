package cn.tedu.schoolshop.getway.service;



import cn.tedu.schoolshop.vo.store.HeaderStoreVo;

import java.util.List;

/**
 * <p>
 * 精品推荐商家 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
public interface HeaderStoreService{
    List<HeaderStoreVo> selectHeaderStore();
}
