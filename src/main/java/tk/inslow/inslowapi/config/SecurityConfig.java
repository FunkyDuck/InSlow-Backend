package tk.inslow.inslowapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends GlobalMethodSecurityConfiguration {
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    private final JwtProvider provider;

    public SecurityConfig(JwtProvider provider) {
        this.provider = provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
//                .ignoringAntMatchers("/h2-console");

        http.headers()
                .frameOptions().sameOrigin();

        http.addFilterBefore(new JwtAuthorizationFilter(provider), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/posts").authenticated()
                .anyRequest().permitAll();

        http.cors();

        System.out.println("Security Config Ended !");

        return http.build();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
