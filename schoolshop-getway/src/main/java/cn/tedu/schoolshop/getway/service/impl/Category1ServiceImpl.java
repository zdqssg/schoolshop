package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.Category1Mapper;
import cn.tedu.schoolshop.getway.service.Category1Service;
import cn.tedu.schoolshop.vo.Category1Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 店铺分类 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-14
 */
@Service
public class Category1ServiceImpl implements Category1Service {

    @Autowired
    private Category1Mapper classifyMapper;
    @Override
    public List<Category1Vo> selectAll() {
        List<Category1Vo> selectAll = classifyMapper.selectAll();
        return selectAll;
    }
}
