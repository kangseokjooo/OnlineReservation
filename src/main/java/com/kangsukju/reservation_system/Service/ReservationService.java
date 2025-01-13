package com.kangsukju.reservation_system.Service;

import com.kangsukju.reservation_system.Dto.ReservationDto;
import com.kangsukju.reservation_system.Dto.UpdateReservationDto;
import com.kangsukju.reservation_system.Entity.Reservation;
import com.kangsukju.reservation_system.Entity.User;
import com.kangsukju.reservation_system.Repository.ReservationRepository;
import com.kangsukju.reservation_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @CachePut(value = "reservations", key = "#reservationDto.id != null ? #reservationDto.id : #reservationDto.userId")
    public Reservation createReservation(ReservationDto reservationDto) {
        User user = userRepository.findByUserid(reservationDto.getUserId());
        if (user == null) {
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

    @CachePut(value = "reservations", key = "#updateReservationDto.id")
    public Reservation updateReservation(UpdateReservationDto updateReservationDto) {
        Reservation reservation = reservationRepository.findById(updateReservationDto.getId())
                .orElseThrow(() -> new RuntimeException("해당 예약이 존재하지 않습니다."));

        reservation.setReservationTime(updateReservationDto.getReservationTime());
        reservation.setStatus(updateReservationDto.getStatus());
        reservation.setLocation(updateReservationDto.getLocation());
        reservation.setDescription(updateReservationDto.getDescription());
        reservation.setPrice((double) updateReservationDto.getPrice());

        return reservationRepository.save(reservation);
    }

    @CacheEvict(value = "reservations", key = "#id")
    public void deleteReservation(String userid, Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 예약이 존재하지 않습니다."));
        reservationRepository.delete(reservation);
    }

    @Cacheable(value = "reservations", key = "#id")
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 예약이 존재하지 않습니다."));
    }

    public List<Reservation> allReservation(String userid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        Page<Reservation> reservationPage = reservationRepository.findByUserid(userid, pageable);

        if (reservationPage.isEmpty()) {
            throw new RuntimeException("해당 유저가 예약한 목록이 없습니다");
        }

        return reservationPage.getContent();
    }
}
