package cn.tedu.schoolshop.getway.controller.RestController;

import cn.tedu.schoolshop.getway.dto.FilterSearchDto;
import cn.tedu.schoolshop.getway.service.SearchService;
import cn.tedu.schoolshop.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/18 13:13
 */
@RestController
@RequestMapping("/api-search")
@CrossOrigin
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 搜索栏模糊搜索
     *
     * @return
     */
    @GetMapping("/searchMatching/{msg}")
    public R searchMatching(@PathVariable("msg") String msg) throws IOException {
        return searchService.searchMatching(msg);
    }
    /**
     * 搜索商品
     *
     * @return
     */
    @PostMapping("")
    public R searchList(@RequestBody FilterSearchDto filter) throws IOException {
        return searchService.search(filter);
    }
}
