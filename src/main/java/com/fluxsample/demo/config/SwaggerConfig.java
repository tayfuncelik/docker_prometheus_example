package com.fluxsample.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.fluxsample.demo.api"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo());
//                .securitySchemes(Arrays.asList(apiKey()));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(" Swagger Doc")
                .description("API Documentation")
                .contact(new Contact("Tayfun Çelik", "", "tayfuncelikeee@gmail.com"))
                .version("0.0.1")
                .build();
    }

//    private ApiKey apiKey() {
//        return new ApiKey("JWT_LOGIN", "Authorization", "header");
//    }

}
