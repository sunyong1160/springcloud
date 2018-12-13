package zipkin2.dependencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableScheduling  // 定时任务配置
public class ZipkinDependenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinDependenceApplication.class, args);
    }
}
