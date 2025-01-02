package com.kangsukju.reservation_system.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userid;


    private String password;

    private String username;

    private String email;

    private String phone;

    private String address;
}
