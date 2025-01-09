package tasklist.tasklist.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tasklist.tasklist.domain.task.Task;
import tasklist.tasklist.domain.user.User;
import tasklist.tasklist.service.TaskService;
import tasklist.tasklist.service.UserService;
import tasklist.tasklist.web.dto.task.TaskDto;
import tasklist.tasklist.web.dto.user.UserDto;
import tasklist.tasklist.web.dto.validation.OnCreate;
import tasklist.tasklist.web.dto.validation.OnUpdate;
import tasklist.tasklist.web.mappers.TaskMapper;
import tasklist.tasklist.web.mappers.UserMapper;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;


    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {

        User updatedUser = userService.update(userMapper.toEntity(userDto));
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {

        log.info("HERE IS ID {}",id);
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{userId}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable Long userId) {
        return taskMapper.toDto(taskService.getAllTasksByUserId(userId));
    }

    @PostMapping("/{userId}/tasks")
    public TaskDto createTask(@PathVariable Long userId, @Validated(OnCreate.class) @RequestBody TaskDto taskDto){
        Task task = taskMapper.toEntity(taskDto);
        Task createdTask = taskService.create(task,userId);
        return taskMapper.toDto(createdTask);
    }

}
