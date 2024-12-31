package tasklist.tasklist.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tasklist.tasklist.domain.task.Task;
import tasklist.tasklist.service.TaskService;
import tasklist.tasklist.web.dto.task.TaskDto;
import tasklist.tasklist.web.dto.validation.OnUpdate;
import tasklist.tasklist.web.mappers.TaskMapper;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private TaskService taskService;
    private TaskMapper taskMapper;



    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task updatedTask = taskService.update(taskMapper.toEntity(dto));
        return taskMapper.toDto(updatedTask);

    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

}
