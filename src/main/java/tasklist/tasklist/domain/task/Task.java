package tasklist.tasklist.domain.task;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Task implements Serializable {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;

}
