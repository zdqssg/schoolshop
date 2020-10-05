package cn.tedu.schoolshop.getway.service.impl;

import cn.tedu.schoolshop.getway.mapper.SearchMsgMapper;
import cn.tedu.schoolshop.getway.service.SearchMsgService;
import cn.tedu.schoolshop.vo.Search.SearchMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 搜索历史 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-17
 */
@Service
public class SearchMsgServiceImpl implements SearchMsgService {

    @Autowired
    private SearchMsgMapper searchMsgMapper;
    @Override
    public List<SearchMsgVo> selectHotSearch() {
        return searchMsgMapper.selectHotSearch();
    }
}
