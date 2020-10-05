package cn.tedu.schoolshop.api.merchant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableWebSecurity
@MapperScan("cn.tedu.schoolshop.api.merchant.mapper")
public class SchoolshopApiMerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolshopApiMerchantApplication.class, args);
    }

}
