package cn.tedu.schoolshop.getway.mapper;

import cn.tedu.schoolshop.vo.Search.SearchMsgVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 搜索历史 Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
@Repository
public interface SearchMsgMapper {
    List<SearchMsgVo> selectHotSearch();

}
