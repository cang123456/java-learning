package com.xiaocang.zhujieaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ZhujieAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhujieAopApplication.class, args);
    }

}
