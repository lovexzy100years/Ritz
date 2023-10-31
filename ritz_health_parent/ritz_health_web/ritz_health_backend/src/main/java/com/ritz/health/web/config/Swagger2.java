package com.ritz.health.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
///@Component
//@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())

                .select()

                //对外暴露服务的包,以controller的方式暴露,所以就是controller的包.

                .apis(RequestHandlerSelectors.basePackage("com.ritz.health.web.controller"))

                .paths(PathSelectors.any())

                .build();

    }



    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()

                .title("预约后台管理服务api")

                .description("预约管理服务接口文档说明")

                .contact(new Contact("laowang", "", "laowang@1000phone.com"))

                .version("1.0")

                .build();

    }
}
