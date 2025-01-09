package tasklist.tasklist.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionBody{

    private String message;
    private Map<String,String> errors;


    public ExceptionBody(String message){
        this.message = message;
    }






}
