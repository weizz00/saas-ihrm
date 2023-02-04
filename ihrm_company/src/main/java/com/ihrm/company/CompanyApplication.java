package com.ihrm.company;

import com.ihrm.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

//1、配置springboot的包saomiao
@SpringBootApplication(scanBasePackages = "com.ihrm")
//2、配置jpa注解扫描
@EntityScan(value = "com.ihrm.domain.company")
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class,args);
        System.out.println("---------------------服务启动成功----------------------------");
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
