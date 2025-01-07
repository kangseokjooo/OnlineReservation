package com.kangsukju.reservation_system.Controller;

import com.kangsukju.reservation_system.Dto.ReservationDto;
import com.kangsukju.reservation_system.Dto.ResultDto;
import com.kangsukju.reservation_system.Entity.Reservation;
import com.kangsukju.reservation_system.Service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Tag(name = "Reservation CRUD", description = "예약 관련 API")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    @ResponseBody
    @Operation(summary = "예약 생성")
    public ResultDto<Reservation> createReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.createReservation(reservationDto);
        return ResultDto.of("200", reservation);
    }
    @PutMapping("/update")
    @ResponseBody
    @Operation(summary = "예약 수정")
    public ResultDto<Reservation> updateReservation(@RequestBody ReservationDto reservationDto) {

        String authenticatedUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (authenticatedUserId == null) {
            throw new RuntimeException("인증되지 않은 사용자입니다.");
        }


        Reservation updatedReservation = reservationService.updateReservation(authenticatedUserId, reservationDto);
        return ResultDto.of("200", updatedReservation);
    }
}
