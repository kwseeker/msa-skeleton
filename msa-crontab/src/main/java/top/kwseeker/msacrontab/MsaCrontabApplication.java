package top.kwseeker.msacrontab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class MsaCrontabApplication {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, This is Crontab Micro service";
    }

    public static void main(String[] args) {
        SpringApplication.run(MsaCrontabApplication.class, args);
    }

}

