package tasklist.tasklist.web.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import tasklist.tasklist.domain.task.Status;
import tasklist.tasklist.web.dto.validation.OnCreate;
import tasklist.tasklist.web.dto.validation.OnUpdate;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    @NotNull(message = "Id is required", groups = {OnCreate.class})
    private Long id;

    @NotNull(message = "Title is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 255, message = "Title must be between 3 and 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @NotNull(message = "Description is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, message = "Description must be at least 3 characters", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

}
