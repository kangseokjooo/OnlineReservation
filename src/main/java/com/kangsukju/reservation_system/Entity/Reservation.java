package com.kangsukju.reservation_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private LocalDateTime reservationTime; //예약시간

    @Column(nullable = false)
    private String status; // 예약상태

    @Column(nullable = false)
    private String location; //예약장소

    @Column(nullable = false)
    private String description; //예약 설명

    @Column(nullable = false)
    private Double price; // 예약 비용

    @ManyToOne
    @JoinColumn(name = "userid",nullable = false)
    @JsonBackReference
    private User user;
}
