package top.kwseeker.msa.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MsaSeataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaSeataApplication.class, args);
    }
}
