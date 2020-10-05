package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.dto.FilterSearchDto;
import cn.tedu.schoolshop.getway.service.SearchService;
import cn.tedu.schoolshop.util.PageInfoUtils;
import cn.tedu.schoolshop.util.R;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/18 13:23
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Value("${project.search.page-size}")
    private Integer pageSize;
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public <T> R search(FilterSearchDto filter) throws IOException {
        log.debug("搜索过滤器{}", filter);
        Integer pageNum = filter.getPageNum();
        if (pageNum == null || pageNum < 0) {
            pageNum = 0;
        }

        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        // SearchSourceBuilder 条件构造
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // TermQueryBuilder 精确查询
        // MatchAllQueryBuilder .....

        //判断查询什么
        String msg = filter.getMsg();

        if (msg != null) {
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("goodsName", msg);
            boolBuilder.must(matchQueryBuilder);
        } else {
            String category = filter.getCategory();
            String[] split = category.split(",");
            if (split.length < 1 || split.length > 3) {
                return R.ok();
            }
            String[] categoryArr = {"category1Id", "category2Id", "category3Id"};
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(categoryArr[split.length - 1], split[split.length - 1]);
            boolBuilder.must(termQueryBuilder);
        }


        //价格范围的设定
        if (filter.getBetweenBy() != null) {
            Integer start = filter.getBetweenStart();
            Integer end = filter.getBetweenEnd();
            RangeQueryBuilder rangequerybuilder = QueryBuilders
                    .rangeQuery("goodsPrice")
                    .from(start).to(end);
            //生成查询
            boolBuilder.filter(rangequerybuilder);
        }


        // HighlightBuilder 构建高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("goodsName");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        sourceBuilder.highlighter(highlightBuilder);
        //排序
        Integer orderBy = filter.getOrderBy();
        String[] fieldName = {"goodsClickCount", "goodsPayCount", "goodsPrice", "rate"};
        FieldSortBuilder fieldSortBuilder = null;
        if (orderBy != null && orderBy >= 0 && orderBy <= 3) {
            fieldSortBuilder = SortBuilders.fieldSort(fieldName[orderBy]);
            Integer orderSort = filter.getOrderSort();
            if (orderSort != null && (orderSort == 0 || orderSort == 1)) {
                if (orderSort == 0) {
                    fieldSortBuilder.order(SortOrder.ASC);
                } else {
                    fieldSortBuilder.order(SortOrder.DESC);
                }
            } else {
                fieldSortBuilder.order(SortOrder.DESC);
            }
            sourceBuilder.sort(fieldSortBuilder);
        }


        //放入条件
        sourceBuilder
                .query(boolBuilder)
                .from(pageNum)
                .size(pageSize)
                .timeout(new TimeValue(60, TimeUnit.SECONDS));
        //索引
        SearchRequest request = new SearchRequest("goods_index");
        request.source(sourceBuilder);

        //执行搜索
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        List<T> list = new ArrayList<>();
        for (SearchHit documentFields : hits.getHits()) {
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField goodsName = highlightFields.get("goodsName");
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            if (goodsName != null) {
                Text[] fragments = goodsName.fragments();
                String new_goodsName = "";
                for (Text fragment : fragments) {
                    new_goodsName += fragment;
                }
                sourceAsMap.put("goodsName", new_goodsName);
            }
            list.add((T) sourceAsMap);
        }
        log.debug("搜索内容{}", list);
        int total = (int) hits.getTotalHits().value;
        PageInfo<T> pageInfo = PageInfoUtils.esToPageInfo(list, pageNum, pageSize, total);


        return R.ok(pageInfo);
    }

    @Override
    public R searchMatching(String msg) throws IOException {
        SearchRequest request = new SearchRequest("goods_index");

        // SearchSourceBuilder 条件构造
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //分页搜索
        sourceBuilder.from(0);
        sourceBuilder.size(5);

        // TermQueryBuilder 精确查询
        // MatchAllQueryBuilder .....
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("goodsName", msg);
        sourceBuilder.query(matchQueryBuilder)
                .timeout(new TimeValue(60, TimeUnit.SECONDS));


        // HighlightBuilder 构建高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("goodsName");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        sourceBuilder.highlighter(highlightBuilder);

        //执行搜索
        request.source(sourceBuilder);


        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit documentFields : hits.getHits()) {
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField goodsName = highlightFields.get("goodsName");
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            if (goodsName != null) {
                Text[] fragments = goodsName.fragments();
                String new_goodsName = "";
                for (Text fragment : fragments) {
                    new_goodsName += fragment;
                }

                sourceAsMap.put("newGoodsName", new_goodsName);
            }
            list.add(sourceAsMap);
        }
        return R.ok(list);

    }
}
