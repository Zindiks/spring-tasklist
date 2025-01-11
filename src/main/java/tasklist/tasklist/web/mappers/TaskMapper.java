package tasklist.tasklist.web.mappers;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Controller;
import tasklist.tasklist.domain.task.Task;
import tasklist.tasklist.web.dto.task.TaskDto;

import java.util.List;

@Mapper(componentModel = "spring")
@Controller
public interface TaskMapper {

    TaskDto toDto(Task task);

    List< TaskDto > toDto(List< Task > tasks);

    Task toEntity(TaskDto dto);
}
