package com.wyj.e_commerce_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wyj.e_commerce_management_system.mapper")
public class ECommerceManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceManagementSystemApplication.class,args);
	}

}
