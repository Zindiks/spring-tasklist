package tasklist.tasklist.repository.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tasklist.tasklist.domain.user.Role;
import tasklist.tasklist.domain.user.User;
import tasklist.tasklist.repository.UserRepository;

import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {


    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void insertUserRole(Long id, Role role) {

    }

    @Override
    public boolean isTaskOwner(Long id, Long taskId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
