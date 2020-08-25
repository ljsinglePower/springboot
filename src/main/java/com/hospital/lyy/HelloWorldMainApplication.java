package com.hospital.lyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*** @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用 */
@SpringBootApplication
@MapperScan("com.hospital.lyy.moudle.login.children.*.mapper")
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
