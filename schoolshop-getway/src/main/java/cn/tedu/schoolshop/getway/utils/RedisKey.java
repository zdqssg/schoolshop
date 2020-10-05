package cn.tedu.schoolshop.getway.utils;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { Redis的key }
 * @email zdq247209@163.com
 * @date 2020/9/15 14:33
 */

public interface RedisKey {
    String CATEGORY1 = "category1";//
    String CATEGORY2 = "category2";//
    String CATEGORY3 = "category3";//



    String SEARCHMSG = "SearchMsg";//头部精选热搜商品
    String HEADERGOODS = "headerProducts";//头部精选商品
    String HEADERSTOREONE = "headerStoreOne";//头部精选商店
    String HEADERSTORES = "headerStores";//头部推荐商家们
    String SLIDELIST = "slideList";//首页轮播图
    String RECOMMENDLIST = "recommendList";//首页推荐商品
    String HOTGOODS = "hotProducts";//首页热门商品


    String PROVINCE="PROVINCE";
    String CITY="CITY";
    String AREA="AREA";
    String STREET="STREET";

    /**
     * 得到CATEGORY1的集合的Key
     *
     * @return CATEGORY1 的集合的Key
     */
    static String getCategory1ListKey() {
        return CATEGORY1;
    }

    /**
     * 通过 category1Id（主键）得到CATEGORY1的集合中的元素的Key
     *
     * @param category1Id category1的主键
     * @return CATEGORY1的集合中的元素的Key
     */
    static String getCategory1ListValueKey(Integer category1Id) {
        return getCategory1ListKey() + "-" + category1Id;
    }

    /**
     * 根据CATEGORY2的外键( category1Id )得到 某个（category1Id）CATEGORY2的集合的Key
     *
     * @param category1Id CATEGORY2的外键( category1Id )
     * @return 某个（ category1Id ）CATEGORY2的集合的Key
     */
    static String getCategory2ListKey(Integer category1Id) {
        return CATEGORY2 + "-" + category1Id;
    }

    /**
     * 根据CATEGORY2的外键( category1Id )得到 某个（category1Id）CATEGORY2的集合的某个(category2Id)元素的Key
     *
     * @param category1Id 根据CATEGORY2的外键( category1Id )
     * @param category2Id category2的主键
     * @return 某个（category1Id）CATEGORY2的集合的某个(category2Id)元素的Key
     */
    static String getCategory2ListValueKey(Integer category1Id, Integer category2Id) {
        return getCategory2ListKey(category1Id) + "-" + category2Id;
    }

    /**
     * 根据CATEGORY3的外键( category2Id )得到 某个（category2Id）CATEGORY3的集合的Key
     *
     * @param category2Id CATEGORY3的外键( category2Id )
     * @return 某个（category2Id）CATEGORY3的集合的Key
     */
    static String getCategory3ListKey(Integer category2Id) {
        return CATEGORY3 + "-" + category2Id;
    }

    /**
     * 根据CATEGORY3的外键( category2Id )得到 某个（category2Id）CATEGORY3的集合的某个(category3Id)元素的Key
     *
     * @param category2Id 根据CATEGORY3的外键( category2Id )
     * @param category3Id category3的主键
     * @return 某个（category2Id）CATEGORY3的集合的某个(category3Id)元素的Key
     */
    static String getCategory3ListValueKey(Integer category2Id, Integer category3Id) {
        return getCategory3ListKey(category2Id) + "-" + category3Id;
    }

}
