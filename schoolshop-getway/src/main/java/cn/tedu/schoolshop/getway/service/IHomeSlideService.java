package cn.tedu.schoolshop.getway.service;


import cn.tedu.schoolshop.model.HomeSlide;
import cn.tedu.schoolshop.vo.home.HomeSlideVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
public interface IHomeSlideService extends IService<HomeSlide> {
    List<HomeSlideVo> selectShowAll();
}
