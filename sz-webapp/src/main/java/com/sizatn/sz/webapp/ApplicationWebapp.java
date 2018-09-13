package com.sizatn.sz.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @desc main方法
 * @author sizatn
 * @date May 5, 2018
 */
@SpringBootApplication
@MapperScan("com.sizatn.sz.webapp.dao")
public class ApplicationWebapp {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationWebapp.class, args);
	}
	
}
