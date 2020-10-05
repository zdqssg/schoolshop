package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.Category1Mapper;
import cn.tedu.schoolshop.getway.mapper.Category2Mapper;
import cn.tedu.schoolshop.getway.service.Category1Service;
import cn.tedu.schoolshop.getway.service.Category2Service;
import cn.tedu.schoolshop.vo.Category1Vo;
import cn.tedu.schoolshop.vo.Category2Vo;
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
public class Category2ServiceImpl implements Category2Service {

    @Autowired
    private Category2Mapper category2Mapper;
    @Override
    public List<Category2Vo> selectAll() {
        List<Category2Vo> selectAll = category2Mapper.selectAll();
        return selectAll;
    }

    @Override
    public List<Category2Vo> selectByCategory1Id(Integer category1Id) {
        return category2Mapper.selectByCategory1Id(category1Id);
    }
}
