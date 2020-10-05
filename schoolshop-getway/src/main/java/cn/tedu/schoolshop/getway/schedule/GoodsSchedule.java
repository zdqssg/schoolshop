package cn.tedu.schoolshop.getway.schedule;

import cn.tedu.schoolshop.getway.mapper.GoodsMapper;
import cn.tedu.schoolshop.model.Goods;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/4 17:05
 */
@Component
@Slf4j
public class GoodsSchedule {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24 * 7)
    public void putData() throws IOException {

        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();

        queryWrapper.ge("goods_state", 0);
        queryWrapper.le("goods_state", 1);
        List<Goods> goods = goodsMapper.selectList(queryWrapper);


        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("goods_index");
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("goods_index");
        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        BulkRequest bulkRequest = new BulkRequest()
                .timeout("2m");
        for (Goods good : goods) {
            bulkRequest.add(new IndexRequest("goods_index")
                    .source(JSON.toJSONString(good), XContentType.JSON));
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }
}
