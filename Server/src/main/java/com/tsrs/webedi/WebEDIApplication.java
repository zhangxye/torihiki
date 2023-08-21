package com.tsrs.webedi;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.tsrs.webedi.config.properties.WebEDIProperties;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot方式启动类
 *
 * @author tsrs
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
public class WebEDIApplication implements WebMvcConfigurer{

    protected final static Logger logger = LoggerFactory.getLogger(WebEDIApplication.class);

    @Autowired
    WebEDIProperties webediProperties;

    /**
     * 增加swagger的支持
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(webediProperties.getSwaggerOpen()){
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }
   @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // TODO Auto-generated method stub
//        super.configureMessageConverters(converters);
        
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastConf = new FastJsonConfig();
        
        fastConf.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastConf);
        
        converters.add(fastJsonConverter);
    }
   
   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")
               .allowedOrigins("http://localhost:8080")
               .allowedMethods("GET", "POST", "PUT", "DELETE")
               .allowCredentials(true)
               .allowedHeaders("Authorization");
   }
   
    public static void main(String[] args) {
        SpringApplication.run(WebEDIApplication.class, args);
        logger.info("WebEDIApplication is sussess!");
    }
}
