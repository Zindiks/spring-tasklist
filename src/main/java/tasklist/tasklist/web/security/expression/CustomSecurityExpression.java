package tasklist.tasklist.web.security.expression;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tasklist.tasklist.domain.user.Role;
import tasklist.tasklist.service.UserService;
import tasklist.tasklist.web.security.JwtEntity;

@Slf4j @Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    private final UserService userService;

    public boolean canAccessUser(Long id) {

        log.info("canAccessUser");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        JwtEntity user = (JwtEntity) authentication.getPrincipal();

        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, Role.ROLE_ADMIN);

    }

    private boolean hasAnyRole(Authentication authentication, Role... roles) {
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());

            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }


    public boolean canAccessTask(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();

        Long id = user.getId();

        return userService.isTaskOwner(id, taskId);

    }
}
