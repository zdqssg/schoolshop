package cn.tedu.schoolshop.getway.service;

import cn.tedu.schoolshop.vo.Search.SearchMsgVo;

import java.util.List;

/**
 * <p>
 * 搜索历史 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
public interface SearchMsgService {
    List<SearchMsgVo> selectHotSearch();
}
