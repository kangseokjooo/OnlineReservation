    package com.kangsukju.reservation_system.Controller;

    import com.kangsukju.reservation_system.Dto.ResultDto;
    import com.kangsukju.reservation_system.Dto.UserDto;
    import com.kangsukju.reservation_system.Entity.User;
    import com.kangsukju.reservation_system.Service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    import java.util.ArrayList;
    import java.util.List;

    @Controller
    public class UserController {
        @Autowired
        private UserService userService;

        @PostMapping("/join")
        @ResponseBody
        public ResultDto<String> signUp(@RequestBody UserDto userDto){
           userService.signUp(userDto);
            return ResultDto.of("200", "회원가입완료");
        }

        @PatchMapping("/update/{userid}")
        @ResponseBody
        public ResultDto<User> updateUser(@PathVariable String userid, @RequestBody UserDto userDto) {
            User updatedUser = userService.updateUser(userid, userDto);

            return ResultDto.of("회원 정보가 수정되었습니다.", updatedUser);
        }

        @DeleteMapping("/delete")
        @ResponseBody
        public ResultDto<String> deleteUser(@RequestBody UserDto userDto) {
            userService.deleteUser(userDto.getUserid());
            return ResultDto.of("200", "회원 삭제");
        }
    }
