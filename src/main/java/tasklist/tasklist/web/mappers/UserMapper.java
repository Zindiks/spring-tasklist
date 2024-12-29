package tasklist.tasklist.web.mappers;

import org.mapstruct.Mapper;
import tasklist.tasklist.domain.user.User;
import tasklist.tasklist.web.dto.user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);

}
