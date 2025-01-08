package tasklist.tasklist.service;


import tasklist.tasklist.domain.task.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);
    List<Task> getAllTasksByUserId(Long userId);
    Task update(Task task);
    Task create(Task task, Long userId);
    void delete(Long id);

}
