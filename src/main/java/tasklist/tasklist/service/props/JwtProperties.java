package tasklist.tasklist.service.props;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Data
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private long access;
    private String secret;
    private long refresh;

}

