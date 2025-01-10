package tasklist.tasklist.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Auth Controller")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    @Operation(summary = "Register user")
    public UserDto register(@Validated @RequestBody UserDto userDto) {
        return userMapper.toDto(userService.create(userMapper.toEntity(userDto)));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh JWT token")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
