package tasklist.tasklist.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tasklist.tasklist.web.dto.validation.OnCreate;
import tasklist.tasklist.web.dto.validation.OnUpdate;

@Schema(description = "DTO representing a user in the system, including essential user details such as id, name," +
        " username, and password.")
@Data
public class UserDto {
    @Schema(description = "User's id", example = "1")
    @NotNull(message = "Id is required", groups = {OnCreate.class})
    private Long id;

    @Schema(description = "User's name", example = "John Doe")
    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters",
            groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Schema(description = "User's username / email", example = "johndoe@gmail.com")
    @NotBlank(message = "Username is required")
    @NotNull(message = "Username is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 50, message = "Username must be between 3 and 50 characters",
            groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Schema(description = "Users's encrypted password", example =
            "$2a$12$CcwA8alsqBvvyZgYGS3rQelMybc5IAkZdWHQQwzwfAxEvsvxJfBmK")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password is required", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Schema(description = "User's password confirmation", example =
            "$2a$12$CcwA8alsqBvvyZgYGS3rQelMybc5IAkZdWHQQwzwfAxEvsvxJfBmK")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation is required", groups = {OnCreate.class})
    private String passwordConfirmation;

}
