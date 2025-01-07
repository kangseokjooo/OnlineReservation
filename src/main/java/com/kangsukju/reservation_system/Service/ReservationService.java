package com.kangsukju.reservation_system.Service;

import com.kangsukju.reservation_system.Dto.ReservationDto;
import com.kangsukju.reservation_system.Entity.Reservation;
import com.kangsukju.reservation_system.Entity.User;
import com.kangsukju.reservation_system.Repository.ReservationRepository;
import com.kangsukju.reservation_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    public Reservation createReservation(ReservationDto reservationDto) {
        User user = userRepository.findByUserid(reservationDto.getUserId());
        if (user==null){
            throw new RuntimeException("해당 유저가 존재하지 않습니다");
        }
        Reservation reservation = new Reservation();
        reservation.setReservationTime(reservationDto.getReservationTime());
        reservation.setStatus(reservationDto.getStatus());
        reservation.setLocation(reservationDto.getLocation());
        reservation.setDescription(reservationDto.getDescription());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setUser(user);

        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(String userId, ReservationDto reservationDto) {
        User user = userRepository.findByUserid(userId);
        if (user == null) {
            throw new RuntimeException("해당 유저가 존재하지 않습니다");
        }

        // 해당 유저의 예약을 찾아 수정
        Reservation reservation = reservationRepository.findByUser(user).orElseThrow(() ->
                new RuntimeException("해당 유저의 예약이 존재하지 않습니다")
        );

        reservation.setReservationTime(reservationDto.getReservationTime());
        reservation.setStatus(reservationDto.getStatus());
        reservation.setLocation(reservationDto.getLocation());
        reservation.setDescription(reservationDto.getDescription());
        reservation.setPrice(reservationDto.getPrice());

        return reservationRepository.save(reservation);
    }
}
