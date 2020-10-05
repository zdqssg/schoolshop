package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.vo.store.HeaderStoresVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下接口吧 }
 * @email zdq247209@163.com
 * @date 2020/9/16 19:42
 */
@Repository
public interface HeaderStoresMapper {
    List<HeaderStoresVo> selectAll();
}
