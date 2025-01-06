package com.kangsukju.reservation_system.Service;

import com.kangsukju.reservation_system.Entity.User;
import com.kangsukju.reservation_system.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        User user = userRepository.findByUserid(userid);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userid);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserid())
                .password(user.getPassword())
                .roles("USER") // 기본 역할 설정
                .build();
    }
}
