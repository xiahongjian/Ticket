package tech.hongjian.ticket.search;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xiahongjian on 2021/6/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void test() {
        System.out.println(client);
    }
}
