    package com.kangsukju.reservation_system.Controller;

    import com.kangsukju.reservation_system.Dto.LoginDto;
    import com.kangsukju.reservation_system.Dto.ResultDto;
    import com.kangsukju.reservation_system.Dto.UserDto;
    import com.kangsukju.reservation_system.Entity.User;
    import com.kangsukju.reservation_system.Security.JwtUtil;
    import com.kangsukju.reservation_system.Service.UserService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.parameters.P;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;



    @Controller
    @Tag(name = "USER CRUD",description = "회원정보 관련 API")
    public class UserController {

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private UserService userService;

        @Autowired
        private JwtUtil jwtUtil;

        @PostMapping("/join")
        @ResponseBody
        @Operation(summary = "회원가입",description = "필요 파라미터 userid,password,username,email,phone,address")
        public ResultDto<String> signUp(@RequestBody UserDto userDto){
           userService.signUp(userDto);
            return ResultDto.of("200", "회원가입완료");
        }

        @PostMapping("/login")
        @ResponseBody
        @Operation(summary = "로그인",description = "필요 파라미터 userid,password")
        @Parameter(name = "userid",description = "유저아이디")
        @Parameter(name = "password",description = "패스워드")
        public ResultDto<String> logIn(@RequestBody LoginDto loginDto){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUserid(), loginDto.getPassword())
            );

            // 인증 성공 시 SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // JWT 생성
            String token = jwtUtil.createToken(loginDto.getUserid());

            return ResultDto.of("200",token);
        }

//        @PostMapping("/logout")
//        public ResultDto<String> logOut(@RequestHeader("Authorization") String token) {
//            // JWT 토큰이 존재하고 "Bearer "로 시작하는지 확인
//            if (token != null && token.startsWith("Bearer ")) {
//                // JWT 토큰에서 "Bearer "를 제거
//                token=token.substring(7);
//            }
//
//            // SecurityContext에서 인증 정보 제거
//            SecurityContextHolder.clearContext();
//
//            return ResultDto.of("200", "로그아웃 성공");
//        }


        @PatchMapping("/update/{userid}")
        @ResponseBody
        @Operation(summary = "회원수정",description = "필요파라미터: user정보")
        public ResultDto<User> updateUser(@PathVariable String userid, @RequestBody UserDto userDto) {
            User updatedUser = userService.updateUser(userid, userDto);

            return ResultDto.of("회원 정보가 수정되었습니다.", updatedUser);
        }

        @DeleteMapping("/delete")
        @ResponseBody
        @Operation(summary = "회원탈퇴",description = "필요 파라미터 userid")
        public ResultDto<String> deleteUser(@RequestBody UserDto userDto) {
            userService.deleteUser(userDto.getUserid());
            return ResultDto.of("200", "회원 삭제");
        }
    }
