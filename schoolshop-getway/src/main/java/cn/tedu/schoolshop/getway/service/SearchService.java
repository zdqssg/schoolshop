package cn.tedu.schoolshop.getway.service;


import cn.tedu.schoolshop.getway.dto.FilterSearchDto;
import cn.tedu.schoolshop.model.Goods;
import cn.tedu.schoolshop.util.R;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下接口吧 }
 * @email zdq247209@163.com
 * @date 2020/9/18 13:22
 */
public interface SearchService {

    <T> R search(FilterSearchDto filter) throws IOException;

    R searchMatching(String msg)throws IOException;
}
