package org.herb.feignclienttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignClientTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientTaskApplication.class, args);
    }

}
