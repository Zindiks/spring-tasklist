package tasklist.tasklist.domain.user;


import jakarta.persistence.Id;
import lombok.Data;
import tasklist.tasklist.domain.task.Task;

import java.util.List;
import java.util.Set;

@Data
public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String passwordConfirmation;
    private Set<Role> roles;
    private List<Task> tasks;



}
