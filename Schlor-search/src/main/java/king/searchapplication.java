package king;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname searchapplication
 * @Description TODO
 * @Date 2020/12/14 16:29
 * @Created by lrf
 */
@SpringBootApplication
@EnableDiscoveryClient
public class searchapplication {
    public static void main(String[] args) {
        SpringApplication.run(searchapplication.class, args);
    }
}
