package top.kwseeker.msa.seata.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("top.kwseeker.msa.seata.aop");
        DbMapper dbMapper = context.getBean(DbMapper.class);
        dbMapper.insert();
    }
}
