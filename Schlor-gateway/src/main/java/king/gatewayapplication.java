package king;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname gatewayapplication
 * @Description TODO
 * @Date 2020/12/19 10:31
 * @Created by lrf
 */
@SpringBootApplication
@EnableDiscoveryClient
public class gatewayapplication {
    public static void main(String[] args) {
        SpringApplication.run(gatewayapplication.class,args);
    }

}
