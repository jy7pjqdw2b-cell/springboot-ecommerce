package com.wyj.e_commerce_management_system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

        // 写一个简单的接口，访问地址是 http://localhost:8080/hello
        @GetMapping("/hello")
        public String hello() {
            return "框架搭好了！加油！";
        }
}
