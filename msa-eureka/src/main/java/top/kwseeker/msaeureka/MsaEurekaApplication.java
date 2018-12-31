package top.kwseeker.msaeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsaEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaEurekaApplication.class, args);
    }

}

