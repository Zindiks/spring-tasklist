package tasklist.tasklist.service.impl;

import org.springframework.stereotype.Service;
import tasklist.tasklist.service.AuthService;
import tasklist.tasklist.web.dto.auth.JwtRequest;
import tasklist.tasklist.web.dto.auth.JwtResponse;


@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
