package tech.hongjian.ticket.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.hongjian.ticket.common.utils.JSONUtil;
import tech.hongjian.ticket.search.bo.BankAccount;
import tech.hongjian.ticket.search.config.ElasticSearchConfiguration;

import java.io.IOException;

/**
 * Created by xiahongjian on 2021/6/22.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void test() {
        System.out.println(client);
    }


    @Test
    public void indexData() throws IOException {
        UserData user = new UserData("Tom", "M", 19);
        IndexRequest indexRequest = new IndexRequest("users").id("1").source(JSONUtil.toJSON(user), XContentType.JSON);
        IndexResponse response = client.index(indexRequest, ElasticSearchConfiguration.COMMON_OPTIONS);
        log.info(JSONUtil.toJSON(response));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UserData {
        private String username;
        private String gender;
        private Integer age;
    }

    @Test
    public void searchData() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.matchQuery("address", "mill"))
                .from(0)
                .size(50)
                .aggregation(AggregationBuilders.terms("ageAgg").field("age").size(10))
                .aggregation(AggregationBuilders.avg("balanceAvg").field("balance"));
        SearchRequest request = new SearchRequest().indices("bank").source(builder);

        SearchResponse response = client.search(request, ElasticSearchConfiguration.COMMON_OPTIONS);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            BankAccount account = JSONUtil.toBean(hit.getSourceAsString(), BankAccount.class);
            log.info("Account: {}", account);
        }
        Aggregations aggregations = response.getAggregations();
        Terms ageAgg = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg.getBuckets()) {
            log.info("年龄：{}, 数量：{}", bucket.getKeyAsString(), bucket.getDocCount());;
        }

        Avg avgAgg = aggregations.get("balanceAvg");
        log.info("Avg Balance: {}", avgAgg.getValue());

        log.info(response.toString());
    }
}
