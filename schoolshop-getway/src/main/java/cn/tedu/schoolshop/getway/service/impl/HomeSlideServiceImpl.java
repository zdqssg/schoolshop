package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.HomeSlideMapper;
import cn.tedu.schoolshop.getway.service.IHomeSlideService;
import cn.tedu.schoolshop.model.HomeSlide;
import cn.tedu.schoolshop.vo.home.HomeSlideVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
@Service
public class HomeSlideServiceImpl extends ServiceImpl<HomeSlideMapper, HomeSlide> implements IHomeSlideService {
    @Autowired
    private HomeSlideMapper slideMapper;

    @Override
    public List<HomeSlideVo> selectShowAll() {
        return slideMapper.selectShowAll();
    }
}
