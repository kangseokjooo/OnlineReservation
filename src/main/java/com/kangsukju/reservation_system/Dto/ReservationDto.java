package com.kangsukju.reservation_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private Long id;
    private LocalDateTime reservationTime;
    private String status;
    private String location;
    private String description;
    private Double price;
    private String userId;
}
