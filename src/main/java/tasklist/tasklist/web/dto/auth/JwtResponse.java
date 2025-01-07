package tasklist.tasklist.web.dto.auth;

import lombok.*;


@Getter
@Setter
public class JwtResponse {

    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;

}