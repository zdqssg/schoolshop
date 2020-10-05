package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.vo.store.HeaderStoreVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 精品推荐商家 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
@Repository
public interface HeaderStoreMapper {
    List<HeaderStoreVo> selectHeaderStore();
}
