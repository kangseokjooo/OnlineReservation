package com.kangsukju.reservation_system.Repository;

import com.kangsukju.reservation_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsById(String userid);


    User findByUserid(String userid);
}
