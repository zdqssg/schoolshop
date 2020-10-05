package cn.tedu.schoolshop.getway.schedule;

import cn.tedu.schoolshop.getway.service.*;
import cn.tedu.schoolshop.getway.utils.RedisKey;
import cn.tedu.schoolshop.getway.utils.RedisUtils;
import cn.tedu.schoolshop.model.BsArea;
import cn.tedu.schoolshop.model.BsCity;
import cn.tedu.schoolshop.model.BsProvince;
import cn.tedu.schoolshop.model.BsStreet;
import cn.tedu.schoolshop.vo.Category1Vo;
import cn.tedu.schoolshop.vo.Category2Vo;
import cn.tedu.schoolshop.vo.Category3Vo;
import cn.tedu.schoolshop.vo.Search.SearchMsgVo;
import cn.tedu.schoolshop.vo.goods.GoodsShowVo;
import cn.tedu.schoolshop.vo.home.HomeSlideVo;
import cn.tedu.schoolshop.vo.store.HeaderStoreVo;
import cn.tedu.schoolshop.vo.store.HeaderStoresVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/15 10:23
 */
@Component
@Slf4j
public class RedisSchedule {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private Category1Service category1Service;
    @Autowired
    private Category2Service category2Service;
    @Autowired
    private Category3Service category3Service;

    @Autowired
    private SearchMsgService headerSearchMsgService;
    @Autowired
    private HeaderProductService headerProductService;
    @Autowired
    private HeaderStoreService headerStoreService;
    @Autowired
    private HeaderStoresService headerStoresService;
    @Autowired
    private IHomeSlideService homeSlideService;
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IBsProvinceService provinceService;
    @Autowired
    private IBsCityService cityService;
    @Autowired
    private IBsAreaService areaService;
    @Autowired
    private IBsStreetService streetService;

    /**
     * 更新Redis服务器中头部的数据
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void updateHeader() {
        log.debug("[{}]准备更新Redis服务器中头部的数据", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));
        //向Redis更新category1的向下的所有的数据开始
        List<Category1Vo> category1Vos = category1Service.selectAll();
        //添加集合
        redisUtils.rightPushList(RedisKey.getCategory1ListKey(), category1Vos);
        for (Category1Vo category1 : category1Vos) {
            Integer category1Id = category1.getId();
            //添加元素
            redisUtils.setValue(RedisKey.getCategory1ListValueKey(category1Id), category1);
            //向Redis更新category1的代码片段结束

            //向Redis更新category1的数据开始
            List<Category2Vo> category2Vos = category2Service.selectByCategory1Id(category1Id);
            //添加集合
            redisUtils.rightPushList(RedisKey.getCategory2ListKey(category1Id), category2Vos);
            for (Category2Vo category2 : category2Vos) {
                Integer category2Id = category2.getId();
                //添加元素
                redisUtils.setValue(RedisKey.getCategory2ListValueKey(category1Id, category2Id), category2);
                //向Redis更新category1的代码片段结束

                //向Redis更新category1的数据开始
                List<Category3Vo> category3Vos = category3Service.selectByCategory2Id(category2Id);
                //添加集合
                redisUtils.rightPushList(RedisKey.getCategory3ListKey(category2Id), category3Vos);
                for (Category3Vo category3 : category3Vos) {
                    Integer category3Id = category3.getId();
                    //添加元素
                    redisUtils.setValue(RedisKey.getCategory3ListValueKey(category2Id, category3Id), category3);
                }
                //向Redis更新category1的代码片段结束
            }
        }


        //查询头部精选热搜商品并向redis更新新数据
        List<SearchMsgVo> searchMsg = headerSearchMsgService.selectHotSearch();
        redisUtils.rightPushList(RedisKey.SEARCHMSG, searchMsg);

        //查询头部推荐商家们~~并向redis更新新数据
        List<HeaderStoresVo> headerStoresVos = headerStoresService.selectAll();
        redisUtils.rightPushList(RedisKey.HEADERSTORES, headerStoresVos);

        //查询头部精选商品并向redis更新新数据
        List<GoodsShowVo> headerProducts = headerProductService.selectAll();
        redisUtils.rightPushList(RedisKey.HEADERGOODS, headerProducts);

        //查询头部精选商家并向redis更新新数据
        List<HeaderStoreVo> headerStoreVos = headerStoreService.selectHeaderStore();
        redisUtils.rightPushList(RedisKey.HEADERSTOREONE, headerStoreVos);
        log.debug("[{}]更新Redis头部的数据完成", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));
    }

    /**
     * 更新Redis服务器中首页的数据
     */
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void updateHomepage() {
        log.debug("[{}]准备更新Redis服务器中首页的数据", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));
        //查询首页轮播图并向redis更新新数据
        List<HomeSlideVo> homeSlideVos = homeSlideService.selectShowAll();
        redisUtils.rightPushList(RedisKey.SLIDELIST, homeSlideVos);
        //查询首页推荐商品并向redis更新新数据
        List<GoodsShowVo> recommendList = goodsService.selectRecommendGoods();
        redisUtils.rightPushList(RedisKey.RECOMMENDLIST, recommendList);
        //查询首页热门商品并向redis更新新数据
        List<GoodsShowVo> hotGoods = goodsService.selectHotProducts();
        redisUtils.rightPushList(RedisKey.HOTGOODS, hotGoods);
        log.debug("[{}]更新Redis首页的数据完成", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));
    }

    /**
     * 更新中国省市区街道4级数据
     */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24 * 7)
    public void updateAddress() {
        log.debug("[{}]准备更新Redis服务器中地址的数据", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));
        //更新省份
        List<BsProvince> provinceList = provinceService.list();
        for (BsProvince province : provinceList) {
            redisUtils.setValue(RedisKey.PROVINCE + province.getProvinceId(), province);
        }
        redisUtils.rightPushList(RedisKey.PROVINCE, provinceList);

         //更新城市
        List<BsCity> cityList = cityService.list();
        for (BsCity city : cityList) {
            redisUtils.setValue(RedisKey.CITY + city.getCityId(), city);
        }
        redisUtils.rightPushList(RedisKey.CITY, cityList);
         //更新地区
        List<BsArea> areaList = areaService.list();
        for (BsArea area : areaList) {
            redisUtils.setValue(RedisKey.AREA + area.getAreaId(), area);
        }
        redisUtils.rightPushList(RedisKey.AREA, areaList);

        //更新街道
        List<BsStreet> streetList = streetService.list();
        for (BsStreet street : streetList) {
            redisUtils.setValue(RedisKey.STREET+street.getStreetId(),street);
        }
        redisUtils.rightPushList(RedisKey.STREET,streetList);
        log.debug("[{}]更新Redis地址的数据完成", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));
    }
}
