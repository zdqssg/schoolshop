package cn.tedu.schoolshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/18 1:27
 */


@SpringBootApplication
public class SchoolshopModelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolshopModelApplication.class, args);
    }
}
