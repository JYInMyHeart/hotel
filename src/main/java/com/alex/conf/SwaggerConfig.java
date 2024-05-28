package com.alex.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger2
@Component("swagger2Config")
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket api() {
        ParameterBuilder tokenParameter = new ParameterBuilder();
        tokenParameter.name("token").description("身份验证token，登录时获取。")
                      .modelRef(new ModelRef("string")).parameterType("header")
                      //.modelRef(new ModelRef("string")).parameterType("cluster_id")
                      .required(false).build();


        List<Parameter> parameters = new ArrayList<>();
        parameters.add(tokenParameter.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alex.controller"))
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Console Server").description("hotel server 接口信息")
                                   .version("1.0").build();
    }

    @Value("${web.upload-path}")
    private String picturePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


        //配置静态资源路径
        registry.addResourceHandler("/static/**")
                .addResourceLocations("resources/", "static/", "public/",
                                      "META-INF/resources/")
                .addResourceLocations("classpath:resources/", "classpath:static/",
                                      "classpath:public/", "classpath:META-INF/resources/","file:" + picturePath);
    }


}