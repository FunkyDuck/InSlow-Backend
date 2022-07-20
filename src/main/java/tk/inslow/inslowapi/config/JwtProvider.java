package tk.inslow.inslowapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tk.inslow.inslowapi.models.entities.Role;
import tk.inslow.inslowapi.services.UsersServices;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static tk.inslow.inslowapi.config.SecurityConstants.*;

@Component
public class JwtProvider {
    @Autowired
    private final UserDetailsService service;

    public JwtProvider(UserDetailsService service) {
        this.service = service;
    }

//    @Bean
//    public UserDetailsService service() {
//        return super.service();
//    }

    public String createToken(String username, Role roles){
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("roles", String.valueOf(roles))
                .sign(Algorithm.HMAC512(JWT_KEY));

        return TOKEN_PREFIX + token;
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_KEY);

        if(token != null && token.startsWith(TOKEN_PREFIX)){
            return token.substring(TOKEN_PREFIX.length());
        }

        return null;
    }

    public boolean validate(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JWT_KEY))
                    .build()
                    .verify(token);

            String username = decodedJWT.getSubject();
            Date expiresAt = decodedJWT.getExpiresAt();

            service.loadUserByUsername(username);

            return username != null && expiresAt.after(new Date());
        }catch (JWTVerificationException | UsernameNotFoundException er) {
            return false;
        }
    }

    public Authentication getAuth(String token){
        String username = JWT.decode(token).getSubject();
        UserDetails user = service.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
    }
}
