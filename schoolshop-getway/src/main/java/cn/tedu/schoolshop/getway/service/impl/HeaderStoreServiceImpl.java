package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.HeaderStoreMapper;
import cn.tedu.schoolshop.getway.service.HeaderStoreService;
import cn.tedu.schoolshop.vo.store.HeaderStoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 精品推荐商家 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
@Service
public class HeaderStoreServiceImpl implements HeaderStoreService {

    @Autowired
    private HeaderStoreMapper storeMapper;

    @Override
    public List<HeaderStoreVo>  selectHeaderStore() {
        return storeMapper.selectHeaderStore();
    }
}
