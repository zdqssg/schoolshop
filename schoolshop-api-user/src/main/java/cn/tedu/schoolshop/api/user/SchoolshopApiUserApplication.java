package cn.tedu.schoolshop.api.user;

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
@MapperScan("cn.tedu.schoolshop.api.user.mapper")
public class SchoolshopApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolshopApiUserApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize(DataSize.ofMegabytes(500));
        multipartConfigFactory.setMaxRequestSize(DataSize.ofMegabytes(500));
        return multipartConfigFactory.createMultipartConfig();
    }
}
