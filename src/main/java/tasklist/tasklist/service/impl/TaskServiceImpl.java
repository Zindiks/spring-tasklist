package tasklist.tasklist.service.impl;

import org.springframework.stereotype.Service;
import tasklist.tasklist.domain.task.Task;
import tasklist.tasklist.service.TaskService;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public Task getById(Long id) {
        return null;
    }

    @Override
    public List<Task> getAllTasksByUserId(Long id) {
        return List.of();
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public Task create(Task task, Long userId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}