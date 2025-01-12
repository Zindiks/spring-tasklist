package tasklist.tasklist.repository.impl;

import lombok.RequiredArgsConstructor;
import tasklist.tasklist.domain.exception.ResourceMappingException;
import tasklist.tasklist.domain.task.Task;
import tasklist.tasklist.repository.DataSourceConfig;
import tasklist.tasklist.repository.TaskRepository;
import tasklist.tasklist.repository.mappers.TaskRowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfig dataSourceConfig;


    @Override
    public Optional<Task> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            String FIND_BY_ID = """
                    SELECT t.id as task_id,
                           t.title as task_title,
                           t.description as task_description,
                           t.due_date as task_due_date,
                           t.status as task_status
                    FROM tasks t
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding task by task_id");
        }
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            String FIND_ALL_BY_USER_ID = """
                    SELECT
                        t.id as task_id,
                        t.title as task_title,
                        t.description as task_description,
                        t.due_date as task_due_date,
                        t.status as task_status
                    FROM tasks t
                            JOIN users_tasks ut on t.id = ut.task_id
                    WHERE ut.user_id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TaskRowMapper.mapRows(rs);
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding tasks by user_id");
        }
    }


    @Override
    public void assignToUserById(Long taskId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            String ASSIGN = """
                    INSERT INTO users_tasks (task_id, user_id)
                    VALUES (?,?)
                    """;
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error to assign task to user");
        }

    }

    @Override
    public void update(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            String UPDATE = """
                    UPDATE tasks
                    SET title = ?,
                        description = ?,
                        due_date = ?,
                        status = ?
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(UPDATE);

            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }

            if (task.getDueDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getDueDate().atStartOfDay()));
            }

            statement.setString(4, task.getStatus().name());

            statement.setLong(5, task.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while updating task");
        }

    }

    @Override
    public void create(Task task) {
        try {
            Connection connection = dataSourceConfig.getConnection();

            String CREATE = """
                    INSERT INTO tasks (title, description, due_date, status)
                    VALUES (?,?,?,?)
                    """;
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }

            if (task.getDueDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getDueDate().atStartOfDay()));
            }
            statement.setString(4, task.getStatus().name());
            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                task.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while creating task");
        }

    }

    @Override
    public void delete(Long id) {

        try {
            Connection connection = dataSourceConfig.getConnection();

            String DELETE = """
                    DELETE FROM tasks
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(DELETE);

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while deleting task");
        }

    }
}
