package zipkin2.dependencies.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zipkin2.dependencies.LogInitializer;
import zipkin2.dependencies.config.ElasticSearchConfig;
import zipkin2.dependencies.elasticsearch.ElasticsearchDependenciesJob;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TaskSchedule {

    private static final Logger log = LoggerFactory.getLogger(TaskSchedule.class);

    @Resource
    private ElasticSearchConfig elasticSearchConfig;


    @Scheduled(cron = "0/5 * * * * *")
    public void task() {
        System.out.println("1111111111");
        Runnable logInitializer = LogInitializer.create("info"); // 日志级别
        logInitializer.run(); // 启动日志打印线程
        ElasticsearchDependenciesJob.builder()
                .logInitializer(logInitializer) // 设置日志打印 线程
                .day(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()) // 生成调用链的日志，这里是生成昨天的
                .hosts(elasticSearchConfig.getHosts())  //  es的集群地址
                .index(elasticSearchConfig.getIndex())  // es的索引名称
                .build()
                .run();
    }

}
