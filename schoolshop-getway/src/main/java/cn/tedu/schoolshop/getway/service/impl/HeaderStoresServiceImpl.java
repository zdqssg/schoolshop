package cn.tedu.schoolshop.getway.service.impl;

import cn.tedu.schoolshop.getway.mapper.HeaderStoresMapper;
import cn.tedu.schoolshop.getway.service.HeaderStoresService;
import cn.tedu.schoolshop.vo.store.HeaderStoresVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/16 19:48
 */
@Service
public class HeaderStoresServiceImpl implements HeaderStoresService {
    @Autowired
    private HeaderStoresMapper storesMapper;
    @Override
    public List<HeaderStoresVo> selectAll() {
        return storesMapper.selectAll();
    }
}
