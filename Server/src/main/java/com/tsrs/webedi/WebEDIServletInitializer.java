package com.tsrs.webedi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * WebEDI Web程序启动类
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-21 9:43
 */
public class WebEDIServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebEDIApplication.class);
    }

}
