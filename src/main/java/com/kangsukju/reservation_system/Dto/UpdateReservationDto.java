package com.kangsukju.reservation_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReservationDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userid;
    private Long id;
    private Integer version;
    private LocalDateTime reservationTime;
    private String status;
    private String location;
    private String description;
    private int price;
}
