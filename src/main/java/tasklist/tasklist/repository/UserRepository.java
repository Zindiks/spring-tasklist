package tasklist.tasklist.repository;


import tasklist.tasklist.domain.user.Role;
import tasklist.tasklist.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void update (User user);
    void create (User user);
    void insertUserRole(Long id, Role role);
    boolean isTaskOwner(Long id, Long taskId);
    void delete(Long id);



}
