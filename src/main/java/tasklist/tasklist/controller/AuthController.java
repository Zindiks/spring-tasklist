package tasklist.tasklist.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tasklist.tasklist.service.AuthService;
import tasklist.tasklist.service.UserService;
import tasklist.tasklist.web.dto.auth.JwtRequest;
import tasklist.tasklist.web.dto.auth.JwtResponse;
import tasklist.tasklist.web.dto.user.UserDto;
import tasklist.tasklist.web.mappers.UserMapper;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;



    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest){
        return authService.login(loginRequest);
    }


    @PostMapping("/register")
    public UserDto register(@Validated @RequestBody UserDto userDto){
        return userMapper.toDto(userService.create(userMapper.toEntity(userDto)));
    }


    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }




}
