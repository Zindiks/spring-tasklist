package tasklist.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import tasklist.tasklist.domain.user.User;
import tasklist.tasklist.service.AuthService;
import tasklist.tasklist.service.UserService;
import tasklist.tasklist.web.dto.auth.JwtRequest;
import tasklist.tasklist.web.dto.auth.JwtResponse;
import tasklist.tasklist.web.security.JwtTokenProvider;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;




    @Override
    public JwtResponse login(JwtRequest loginRequest) {

        log.info("Login request: {}", loginRequest);

        JwtResponse jwtResponse = new JwtResponse();


        // blocker
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));







        User user = userService.getByUsername(loginRequest.getUsername());

        log.info("User request: {}", user);

        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));


        log.info("JWTREspomse {}", jwtResponse);

        return jwtResponse;


    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
