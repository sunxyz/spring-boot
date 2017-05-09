package cn.sunxyz.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * Created by yangrd on 2017/5/8.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket configSpringfoxDocket_all(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .select().paths(regex("/api.*"))
                .build();
    }

    @Bean
    public Docket createUserInfoRestApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.sunxyz.controller"))
                .paths(regex("/api/user.*"))
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Springfox REST API")
                .description("Descriptions.")
                .termsOfServiceUrl("http://springfox.io")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

}
