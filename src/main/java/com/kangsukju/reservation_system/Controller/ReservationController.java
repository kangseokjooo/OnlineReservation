package com.kangsukju.reservation_system.Controller;

import com.kangsukju.reservation_system.Dto.ReservationDto;
import com.kangsukju.reservation_system.Dto.ResultDto;
import com.kangsukju.reservation_system.Dto.UpdateReservationDto;
import com.kangsukju.reservation_system.Entity.Reservation;
import com.kangsukju.reservation_system.Service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
@Tag(name = "Reservation CRUD", description = "예약 관련 API")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    @ResponseBody
    @Operation(summary = "예약 생성")
    public ResultDto<Reservation> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.createReservation(reservationDto);
        return ResultDto.of("200", reservation);
    }

    @PatchMapping("/update")
    @ResponseBody
    @Operation(summary = "예약 수정")
    public ResultDto<Reservation> updateReservation(@Valid @RequestBody UpdateReservationDto updateReservationDto) {
        Reservation updatedReservation = reservationService.updateReservation(updateReservationDto);
        return ResultDto.of("200", updatedReservation);
    }

    @DeleteMapping("/delete/{userid}/{id}")
    @ResponseBody
    @Operation(summary = "예약 삭제")
    public ResultDto<String> deleteReservation(@Valid @PathVariable String userid, @Valid @PathVariable Long id) {
        reservationService.deleteReservation(userid, id);
        return ResultDto.of("200", "예약이 성공적으로 삭제되었습니다.");
    }

    @GetMapping("/all/{userid}")
    @ResponseBody
    @Operation(summary = "해당 유저의 전체 예약목록 확인")
    public ResultDto<List<Reservation>> allReservation(@Valid @PathVariable String userid, @Valid @RequestParam int page, @Valid @RequestParam int size) {
        return ResultDto.of("200", reservationService.allReservation(userid, page, size));
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Operation(summary = "특정 예약 조회 (캐싱 적용)")
    public ResultDto<Reservation> getReservation(@PathVariable Long id) {
        return ResultDto.of("200", reservationService.getReservation(id));
    }
}
