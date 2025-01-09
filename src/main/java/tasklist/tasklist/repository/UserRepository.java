package tasklist.tasklist.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tasklist.tasklist.domain.user.Role;
import tasklist.tasklist.domain.user.User;

import java.util.Optional;

@Mapper
public interface UserRepository {

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void update (User user);
    void create (User user);
    void insertUserRole(@Param("userId") Long id, @Param("role") Role role);
    boolean isTaskOwner(@Param("userId") Long id, @Param("taskId") Long taskId);
    void delete(Long id);



}
