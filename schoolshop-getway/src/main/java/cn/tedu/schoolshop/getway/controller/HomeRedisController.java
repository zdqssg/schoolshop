package cn.tedu.schoolshop.getway.controller;

import cn.tedu.schoolshop.getway.utils.RedisKey;
import cn.tedu.schoolshop.getway.utils.RedisUtils;
import cn.tedu.schoolshop.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/16 9:41
 */
@RestController
@RequestMapping("/home")
@CrossOrigin
@Slf4j
public class HomeRedisController {
    @Autowired
    private RedisUtils redisUtils;


    /**
     * 得到category1 List
     *
     * @return category1的集合
     */
    @GetMapping("/category1")
    public R category1List() {
        return R.ok(redisUtils.listRange(RedisKey.CATEGORY1));
    }

    /**
     * 得到category1 List中的某个(category1ID)元素
     *
     * @param category1ID category1的主键
     * @return CATEGORY1的集合中的元素
     */
    @GetMapping("/category1/{category1ID}")
    public R getCategory1ListValue(@PathVariable("category1ID") Integer category1ID) {
        return R.ok(redisUtils.getValue(RedisKey.getCategory1ListValueKey(category1ID)));
    }

    /**
     * 根据CATEGORY2的外键( category1Id )得到 某个（category1Id）CATEGORY2的集合
     *
     * @param category1ID CATEGORY2的外键( category1Id )
     * @return 某个（ category1Id ）CATEGORY2的集合
     */
    @GetMapping("/category2/{category1ID}")
    public R category2List(@PathVariable("category1ID") Integer category1ID) {
        return R.ok(redisUtils.listRange(RedisKey.getCategory2ListKey(category1ID)));
    }

    /**
     * 某个（category1Id）CATEGORY2的集合的某个(category2Id)元素
     *
     * @param category1ID CATEGORY2的外键
     * @param category2ID category2的主键
     * @return 某个（category1Id）CATEGORY2的集合的某个(category2Id)元素
     */
    @GetMapping("/category2/{category1ID}/{category2ID}")
    public R category2ListValue(@PathVariable("category1ID") Integer category1ID,
                                @PathVariable("category2ID") Integer category2ID) {
        return R.ok(redisUtils.listRange(RedisKey.getCategory2ListValueKey(category1ID, category2ID)));
    }


    /**
     * 某个（category2Id）CATEGORY3的集合
     *
     * @param category2ID CATEGORY3的外键
     * @return 某个（category2Id）CATEGORY3的集合
     */
    @GetMapping("/category3/{category2ID}")
    public R category3List(@PathVariable("category2ID") Integer category2ID) {
        return R.ok(redisUtils.listRange(RedisKey.getCategory3ListKey(category2ID)));
    }

    /**
     * 某个（category2Id）CATEGORY3的集合的某个(category3Id)元素
     *
     * @param category2ID CATEGORY3的外键
     * @param category3ID category3的主键
     * @return 某个（category2Id）CATEGORY3的集合的某个(category3Id)元素
     */
    @GetMapping("/category3/{category2ID}/{category3ID}")
    public R category3ListValue(@PathVariable("category2ID") Integer category2ID,
                                @PathVariable("category3ID") Integer category3ID) {
        return R.ok(redisUtils.listRange(RedisKey.getCategory3ListValueKey(category2ID, category3ID)));
    }


    /**
     * 头部热搜商品
     *
     * @return
     */
    @GetMapping("/selectHotSearch")
    public R siftSearchGoods() {
        return R.ok(redisUtils.listRange(RedisKey.SEARCHMSG));
    }

    /**
     * 头部精选商品
     *
     * @return
     */
    @GetMapping("/headerProducts")
    public R headerProducts() {
        return R.ok(redisUtils.listRange(RedisKey.HEADERGOODS));
    }

    /**
     * 头部精选商家
     *
     * @return
     */
    @GetMapping("/headerStore")
    public R headerStore() {
        return R.ok(redisUtils.listRange(RedisKey.HEADERSTOREONE));
    }

    /**
     * 头部推荐商家们
     *
     * @return
     */
    @GetMapping("/headerStores")
    public R headerStores() {
        return R.ok(redisUtils.listRange(RedisKey.HEADERSTORES));
    }

    /**
     * 首页轮播图
     *
     * @return
     */
    @GetMapping("/slideList")
    public R slideList() {
        return R.ok(redisUtils.listRange(RedisKey.SLIDELIST));
    }

    /**
     * 首页推荐商品
     *
     * @return
     */
    @GetMapping("/recommendList")
    public R recommendList() {
        return R.ok(redisUtils.listRange(RedisKey.RECOMMENDLIST));
    }

    /**
     * 首页热门商品
     *
     * @return
     */
    @GetMapping("/hotProducts")
    public R hotProducts() {
        return R.ok(redisUtils.listRange(RedisKey.HOTGOODS));
    }


    @GetMapping("/provinceList")
    public R getProvinceList() {
        return R.ok(redisUtils.listRange(RedisKey.PROVINCE));
    }

    @GetMapping("/cityList")
    public R getcityList() {
        return R.ok(redisUtils.listRange(RedisKey.CITY));
    }
    @GetMapping("/areaList")
    public R areaList() {
        return R.ok(redisUtils.listRange(RedisKey.AREA));
    }

    @GetMapping("/streetList")
    public R streetList() {
        return R.ok(redisUtils.listRange(RedisKey.STREET));
    }


}
