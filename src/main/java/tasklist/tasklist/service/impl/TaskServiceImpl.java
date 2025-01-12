package tasklist.tasklist.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tasklist.tasklist.domain.exception.ResourceNotFoundException;
import tasklist.tasklist.domain.task.Status;
import tasklist.tasklist.domain.task.Task;
import tasklist.tasklist.repository.TaskRepository;
import tasklist.tasklist.service.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "TaskService::getById", key = "#id")
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List< Task > getAllTasksByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    @CachePut(value = "TaskService::update", key = "#task.id")
    public Task update(Task task) {
        if (task.getStatus() == null) {task.setStatus(Status.TODO);}
        taskRepository.update(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {
        task.setStatus(Status.TODO);

        if (task.getDueDate() == null) {
            task.setDueDate(LocalDate.from(LocalDateTime.now().plusWeeks(1)));
        }
        taskRepository.create(task);
        taskRepository.assignToUserById(task.getId(), userId);
        return task;
    }

    @Override
    @Transactional
    @CacheEvict(value = "TaskService::getById", key = "#id")
    public void delete(Long id) {
        taskRepository.delete(id);
    }
}