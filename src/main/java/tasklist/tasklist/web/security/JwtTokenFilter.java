package tasklist.tasklist.web.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import tasklist.tasklist.domain.exception.ResourceNotFoundException;

import java.io.IOException;


@Slf4j
@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        String bearerToken = ((HttpServletRequest) servletRequest)
                .getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
        }

        if (bearerToken != null
                && jwtTokenProvider.isValid(bearerToken)) {
            try{
            Authentication authentication = jwtTokenProvider.getAuthentication(bearerToken);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            } catch (ResourceNotFoundException ignored) {
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}