package com.survey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter {
    @Value("${project.name}")
    private String projectName;
    @Value("${project.version}")
    private String projectVersion;
    @Value("${project.swagger.url}")
    private String swaggerUrl;
    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/**"))
                //.paths(PathSelectors.ant("/rest/v1/**"))
                .apis(RequestHandlerSelectors.basePackage("com."))
                .build()
                .apiInfo(apiDetails());
    }
    //title, String description, String version, String termsOfServiceUrl, String contactName, String license, String licenseUrl
    private ApiInfo apiDetails(){
        return new ApiInfo(
                projectName,
                "Survey Details Api Descriptions",
                projectVersion,
                "http://localhost:8081",
                new Contact("contact Name", "http://localhost:8081", "temporary@gmail.com"),
                "license none",
                "http://localhost:8081",
                Collections.emptyList()

        );
    }
}
