package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.Category3Mapper;
import cn.tedu.schoolshop.getway.service.Category3Service;
import cn.tedu.schoolshop.vo.Category3Vo;
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
public class Category3ServiceImpl implements Category3Service {

    @Autowired
    private Category3Mapper category3Mapper;
    @Override
    public List<Category3Vo> selectAll() {
        List<Category3Vo> selectAll = category3Mapper.selectAll();
        return selectAll;
    }

    @Override
    public List<Category3Vo> selectByCategory2Id(Integer category2Id) {
        return category3Mapper.selectByCategory2Id(category2Id);
    }
}
