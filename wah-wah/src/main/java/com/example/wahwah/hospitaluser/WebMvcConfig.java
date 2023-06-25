package com.example.wahwah.hospitaluser;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/profile/**")
				.addResourceLocations("file:///c:/Repository/profile/");
		
		registry.addResourceHandler("/file/**")
				.addResourceLocations("file:///c:/Repository/file/");
		
		registry.addResourceHandler("/static/**")
		.addResourceLocations("classpath:/static/");
		
		
		registry.addResourceHandler("/css/**")
		.addResourceLocations("classpath:/static/css/");
		
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("classpath:/static/images/");
		
		registry.addResourceHandler("/fonts/**")
		.addResourceLocations("classpath:/static/fonts/");
		
		registry.addResourceHandler("/js/**")
		.addResourceLocations("classpath:/static/js/");
		
		registry.addResourceHandler("/assets/**")
		.addResourceLocations("classpath:/static/assets/");
		
		registry.addResourceHandler("/vendor/**")
		.addResourceLocations("classpath:/static/vendor/");
	}
}
