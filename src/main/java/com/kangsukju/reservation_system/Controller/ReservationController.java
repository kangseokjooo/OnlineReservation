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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservation CRUD", description = "예약 관련 API")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    @Operation(summary = "예약 생성")
    public ResponseEntity<ResultDto<Reservation>> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.createReservation(reservationDto);
        return ResponseEntity.ok(ResultDto.of("200", reservation));
    }

    @PatchMapping("/update")
    @Operation(summary = "예약 수정")
    public ResponseEntity<ResultDto<Reservation>> updateReservation(@Valid @RequestBody UpdateReservationDto updateReservationDto) {
        Reservation updatedReservation = reservationService.updateReservation(updateReservationDto);
        return ResponseEntity.ok(ResultDto.of("200", updatedReservation));
    }

    @DeleteMapping("/delete/{userid}/{id}")
    @Operation(summary = "예약 삭제")
    public ResponseEntity<ResultDto<String>> deleteReservation(@Valid @PathVariable String userid, @Valid @PathVariable Long id) {
        reservationService.deleteReservation(userid, id);
        return ResponseEntity.ok(ResultDto.of("200", "예약이 성공적으로 삭제되었습니다."));
    }

    @GetMapping("/all/{userid}")
    @Operation(summary = "해당 유저의 전체 예약목록 확인")
    public ResponseEntity<ResultDto<List<Reservation>>> allReservation(@Valid @PathVariable String userid, @Valid @RequestParam int page, @Valid @RequestParam int size) {
        List<Reservation> reservations = reservationService.allReservation(userid, page, size);
        return ResponseEntity.ok(ResultDto.of("200", reservations));
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 예약 조회 (캐싱 적용)")
    public ResponseEntity<ResultDto<Reservation>> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservation(id);
        return ResponseEntity.ok(ResultDto.of("200", reservation));
    }
}