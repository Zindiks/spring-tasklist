package tasklist.tasklist.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "Response DTO from login")
public class JwtResponse {

    @Schema(description = "User ID", example = "1")
    private Long id;
    @Schema(description = "User's username / email", example = "johndoe@gmail.com")
    private String username;
    @Schema(description = "User Access Token", example = "eyJhbGciOiJIUzM4NCJ9" +
            ".eyJzdWIiOiJqb2huZG9lQGdtYWlsLmNvbSIsImlkIjoxLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTczNjUxNTU1MiwiZXhwIjoxNDY5NjUxNTU1Mn0.sWtYZGGU4zTtXPR8Plx_K1rAYF1Q4kWfAhAToAyroFcnTyYm6VN1af_0DrJSwAkz")
    private String accessToken;

    @Schema(description = "User Refresh Token", example = "eyJhbGciOiJIUzM4NCJ9" +
            ".eyJzdWIiOiJqb2huZG9lQGdtYWlsLmNvbSIsImlkIjoxLCJleHAiOjc0NjY2OTY1MTU1NTJ9.s6jKeUUm8hzK3ipO_qxkF1TbRNbBjJVhV_9aaQTZZQrGDM2Nn_H_p5X1ucTlgwzt")
    private String refreshToken;


}
