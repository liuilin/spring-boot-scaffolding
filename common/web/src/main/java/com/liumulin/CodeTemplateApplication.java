package com.liumulin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liumulin.mapper")
public class CodeTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeTemplateApplication.class, args);
    }

}
