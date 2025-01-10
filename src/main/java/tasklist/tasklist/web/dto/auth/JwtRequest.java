package tasklist.tasklist.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request DTO for Login")
public class JwtRequest {

    @Schema(description = "User's username / email", example = "johndoe@gmail.com")
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(description = "User's raw password", example = "password")
    @NotBlank(message = "Password is required")
    private String password;

}
