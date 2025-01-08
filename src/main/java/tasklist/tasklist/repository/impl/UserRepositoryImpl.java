package tasklist.tasklist.repository.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tasklist.tasklist.domain.exception.ResourceMappingException;
import tasklist.tasklist.domain.user.Role;
import tasklist.tasklist.domain.user.User;
import tasklist.tasklist.repository.DataSourceConfig;
import tasklist.tasklist.repository.UserRepository;
import tasklist.tasklist.repository.mappers.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DataSourceConfig dataSourceConfig;

    @Override
    public Optional<User> findById(Long id) {
        try{
            Connection connection = dataSourceConfig.getConnection();
            String FIND_BY_ID = """
                    SELECT
                        u.id as user_id,
                        u.name as user_name,
                        u.username as user_username,
                        u.password as user_password,
                        ur.role as user_role_role,
                        t.id as task_id,
                        t.title as task_title,
                        t.description as task_description,
                        t.due_date as task_due_date,
                        t.status as task_status
                    FROM users u
                        LEFT JOIN users_roles ur on u.id = ur.user_id
                        LEFT JOIN users_tasks ut on u.id = ut.user_id
                        LEFT JOIN tasks t on ut.task_id = t.id
                    WHERE u.id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setLong(1,id);
            try(ResultSet rs = statement.executeQuery()){
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding user by id");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try{
            Connection connection = dataSourceConfig.getConnection();

            String FIND_BY_USERNAME = """
                    SELECT
                        u.id as user_id,
                        u.name as user_name,
                        u.username as user_username,
                        u.password as user_password,
                        ur.role as user_role_role,
                        t.id as task_id,
                        t.title as task_title,
                        t.description as task_description,
                        t.due_date as task_due_date,
                        t.status as task_status
                    FROM users u
                        LEFT JOIN users_roles ur on u.id = ur.user_id
                        LEFT JOIN users_tasks ut on u.id = ut.user_id
                        LEFT JOIN tasks t on ut.task_id = t.id
                    WHERE u.username = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.setString(1,username);
            try(ResultSet rs = statement.executeQuery()){
                return Optional.ofNullable(UserRowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding user by username");
        }
    }

    @Override
    public void update(User user) {
        try{
            Connection connection = dataSourceConfig.getConnection();
            String UPDATE = """
                    UPDATE users
                    SET name = ?,
                        username = ?,
                        password = ?
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(UPDATE);

            statement.setString(1, user.getName());
            statement.setString(2,user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while updating user");
        }

    }

    @Override
    public void create(User user) {
        try{
            Connection connection = dataSourceConfig.getConnection();
            String CREATE = """
                    INSERT INTO users (name,username,password)
                    VALUES (?,?,?)
                    """;
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setString(2,user.getUsername());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();

            try(ResultSet rs = statement.getGeneratedKeys()){
                rs.next();
                user.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while creating user");
        }

    }

    @Override
    public void insertUserRole(Long id, Role role) {
        try{
            Connection connection = dataSourceConfig.getConnection();
            String INSERT_USER_ROLE = """
                    INSERT INTO users_roles (user_id, role)
                    VALUES (?,?)
                    """;
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE);

            statement.setLong(1, id);
            statement.setString(2,role.name());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while inserting user role");
        }

    }

    @Override
    public boolean isTaskOwner(Long id, Long taskId) {
        try{
            Connection connection = dataSourceConfig.getConnection();

            String IS_TASK_OWNER = """
                    SELECT exists(
                        SELECT 1
                        FROM users_tasks
                        WHERE user_id = ?
                        AND task_id = ?
                    )
                    """;
            PreparedStatement statement = connection.prepareStatement(IS_TASK_OWNER);

            statement.setLong(1, id);
            statement.setLong(2, taskId);
            try (ResultSet rs = statement.executeQuery()){
                rs.next();
                return rs.getBoolean(1);
            }

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while checking users ownership of task");
        }
    }

    @Override
    public void delete(Long id) {

        try{
            Connection connection = dataSourceConfig.getConnection();
            String DELETE = """
                    DELETE FROM users
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new ResourceMappingException("Error while deleting user");
        }


    }
}
