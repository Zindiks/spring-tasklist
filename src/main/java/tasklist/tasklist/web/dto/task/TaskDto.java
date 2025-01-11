package tasklist.tasklist.web.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import tasklist.tasklist.domain.task.Status;
import tasklist.tasklist.web.dto.validation.OnCreate;
import tasklist.tasklist.web.dto.validation.OnUpdate;

import java.time.LocalDateTime;

@Schema(description = "Request DTO for Tasks")
@Data
public class TaskDto {
    @Schema(description = "Task id", example = "4")
    @NotNull(message = "Id is required", groups = {OnUpdate.class})
    private Long id;

    @Schema(description = "Task title", example = "Call Milf")
    @NotBlank(message = "Title is required")
    @NotNull(message = "Title is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 255, message = "Title must be between 3 and 255 characters",
            groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Schema(description = "Task Description", example = "Ask about meeting")
    @NotNull(message = "Description is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, message = "Description must be at least 3 characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @Schema(description = "Task Status", example = "TODO")
    private Status status;

    @Schema(description = "Task due date", example = "2023-02-01 00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

}
