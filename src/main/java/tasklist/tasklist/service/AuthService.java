package tasklist.tasklist.service;

import tasklist.tasklist.web.dto.auth.JwtRequest;
import tasklist.tasklist.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
