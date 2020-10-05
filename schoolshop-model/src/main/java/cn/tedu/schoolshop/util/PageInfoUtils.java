package cn.tedu.schoolshop.util;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/25 19:48
 */
public class PageInfoUtils {
    /**
     * 一个对象一对多分页的封装
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T oneJavaBeanToPageInfo(List<?> list) {
        PageInfo p = new PageInfo(list);
        Map map = new HashMap();
        map.put("list", p.getList().get(0));
        map.put("total", p.getTotal());
        map.put("pageNum", p.getPageNum());
        map.put("pages", p.getPages());
        map.put("hasPreviousPage", p.isHasPreviousPage());
        map.put("prePage", p.getPrePage());
        map.put("navigatepageNums", p.getNavigatepageNums());
        map.put("hasNextPage", p.isHasNextPage());
        map.put("nextPage", p.getNextPage());
        return (T) map;
    }

    /**
     * ElasticSearch结果分页封装
     */
    public static <T> PageInfo<T> esToPageInfo(List<T> list, Integer pageNum, Integer pageSize, Integer total) {

        //封装
        PageInfo<T> page = new PageInfo<>();
        page.setList(list);
        page.setPageNum(pageNum+1);
        page.setPageSize(pageSize);
        page.setTotal(total);
        page.setPages(total == 0 ? 0 : (total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1));
        page.setHasNextPage(page.getPageNum() < page.getPages());
        return page;
    }

}
