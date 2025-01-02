package com.kangsukju.reservation_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "password",length = 255,nullable = false)
    private String password;
    @Column(name="username",length = 100,nullable = false)
    private String username;
    @Column(name="email",length = 255,nullable = false)
    private String email;
    @Column(name="phone",length = 100,nullable = false)
    private String phone;
    @Column(name="address",length = 255,nullable = false)
    private String address;
}
