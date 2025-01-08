package com.kangsukju.reservation_system.Repository;

import com.kangsukju.reservation_system.Entity.Reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM Reservation r  WHERE r.user.userid=:userid AND r.id=:id")
    Reservation findReservationByUseridAndId(@Param("userid")String userid,@Param("id") Long id);

    @Query("SELECT r FROM Reservation r  JOIN FETCH r.user WHERE r.user.userid = :userid")
    Page<Reservation> findByUserid(@Param("userid") String userid,Pageable pageable);

    @Query("SELECT r FROM Reservation r  JOIN FETCH r.user WHERE r.user.userid = :userid AND r.id = :id")
    Reservation findByUserAndId(String userid, Long id);
}
