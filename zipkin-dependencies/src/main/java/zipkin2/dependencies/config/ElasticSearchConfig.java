package zipkin2.dependencies.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("zipkin.storage.elasticsearch")
@Component
public class ElasticSearchConfig {

    // es的集群地址
    private String hosts;
    // 集群名称
    private String cluster;
    // 索引名称
    private String index;
}
