package org.abhishek.om.config;

import org.abhishek.om.interceptor.PersonAppInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by sca820 on 30 aug., 2022
 * https://www.jacobquatier.com/spring-mapped-interceptors/
 * https://stackoverflow.com/questions/20243130/mvc-java-config-handlerinterceptor-not-excluding-paths
 * https://stackoverflow.com/questions/31082981/spring-boot-adding-http-request-interceptors
 * https://stackoverflow.com/questions/38360215/how-to-create-a-spring-interceptor-for-spring-restful-web-services
 */
@Configuration
public class PersonAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println(">>>>>>>>>>>>>>>>>>> addInterceptors on PersonAppConfig class was called");
        //WebMvcConfigurer.super.addInterceptors(registry);
        //registry.addInterceptor(new PersonAppInterceptor());
        registry.addInterceptor(new PersonAppInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/getAllPersons");
        //registry.addInterceptor(employeeInterceptor).addPathPatterns("/employee");
        //registry.addInterceptor(new MyCustomInterceptor()).addPathPatterns("/**");
    }
}
