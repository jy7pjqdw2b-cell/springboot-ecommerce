package com.wyj.e_commerce_management_system.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String phone;
    private LocalDateTime createTime;
}
