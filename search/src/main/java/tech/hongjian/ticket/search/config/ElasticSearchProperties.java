package tech.hongjian.ticket.search.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by xiahongjian on 2021/6/22.
 */
@Data
@Component
@ConfigurationProperties(prefix = "system.elastic-search")
public class ElasticSearchProperties {
    private String host;
    private Integer port;
    private String schema;
}
