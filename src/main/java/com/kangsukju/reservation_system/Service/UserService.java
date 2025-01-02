package com.kangsukju.reservation_system.Service;

import com.kangsukju.reservation_system.Dto.UserDto;
import com.kangsukju.reservation_system.Entity.User;
import com.kangsukju.reservation_system.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
    public void signUp(UserDto userDto){
        if(userRepository.existsById(userDto.getUserid())){
            throw new RuntimeException("이미 사용중인 ID입니다");
        }
        User user=new User();
        user.setUserid(userDto.getUserid());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());

        userRepository.save(user);
    }
    public User updateUser(String userid, UserDto userDto) {
        User user = userRepository.findByUserid(userid);  // userid로 사용자 조회

        if (user == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        // 변경된 값만 업데이트
        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));  // 비밀번호 암호화
        }
        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }
        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }

        // 업데이트된 사용자 정보 저장
        return userRepository.save(user);  // 수정된 User를 반환
    }
    public void deleteUser(String userid) {
        User user = userRepository.findByUserid(userid);
        if (user == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        userRepository.delete(user);
    }

}
