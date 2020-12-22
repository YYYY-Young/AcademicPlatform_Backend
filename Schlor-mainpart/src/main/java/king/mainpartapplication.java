package king;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Classname mainpartapplication
 * @Description TODO
 * @Date 2020/12/8 22:06
 * @Created by lrf
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class mainpartapplication {
    public static void main(String[] args) {
        SpringApplication.run(mainpartapplication.class, args);
    }
}

