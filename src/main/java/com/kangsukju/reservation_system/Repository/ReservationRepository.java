package com.kangsukju.reservation_system.Repository;

import com.kangsukju.reservation_system.Entity.Reservation;
import com.kangsukju.reservation_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findByUser(User user);
}
