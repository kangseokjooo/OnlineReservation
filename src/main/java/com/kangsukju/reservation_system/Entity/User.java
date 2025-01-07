package com.kangsukju.reservation_system.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Reservation> reservations=new ArrayList<>();


}
