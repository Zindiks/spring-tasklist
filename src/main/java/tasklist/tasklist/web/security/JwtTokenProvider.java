package tasklist.tasklist.web.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tasklist.tasklist.domain.exception.AccessDeniedException;
import tasklist.tasklist.domain.user.Role;
import tasklist.tasklist.domain.user.User;
import tasklist.tasklist.service.UserService;
import tasklist.tasklist.service.props.JwtProperties;
import tasklist.tasklist.web.dto.auth.JwtResponse;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtTokenProvider {


    private JwtProperties jwtProperties;

    private UserDetailsService userDetailsService;

    private UserService userService;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(Long userId, String username, Set<Role> roles){
        Claims claims = (Claims) Jwts.claims().subject(username);
        claims.put("id",userId);
        claims.put("roles", resolveRoles(roles));
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getAccess());
        return Jwts.builder().claims(claims).issuedAt(now).expiration(validity).signWith(key).compact();
    }

    public String createRefreshToken(Long userId,String username){
        Claims claims = (Claims) Jwts.claims().subject(username);
        claims.put("id",userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getRefresh());
        return Jwts.builder().claims(claims).issuedAt(now).expiration(validity).signWith(key).compact();

    }

    public JwtResponse refreshUserTokens(String refreshToken){
        JwtResponse jwtResponse = new JwtResponse();

        if(!validateToken(refreshToken)){
            throw new AccessDeniedException();
        }
        Long userId = Long.valueOf(getId(refreshToken));
        User user = userService.getById(userId);

        jwtResponse.setId(userId);
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(createAccessToken(userId, user.getUsername(), user.getRoles()));
        jwtResponse.setRefreshToken(createRefreshToken(userId, user.getUsername()));

        return jwtResponse;

    }


    public boolean validateToken(String token){

        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token);

        return !claims.getPayload().getExpiration().before(new Date());

    }

    private String getId(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token).getPayload().get("id").toString();
    }


    private String getUsername(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }


    public Authentication getAuthentication(String token){
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }




    private List<String> resolveRoles(Set<Role> roles){
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toList());

    }


}
