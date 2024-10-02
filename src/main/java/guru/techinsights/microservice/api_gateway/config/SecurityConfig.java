package guru.techinsights.microservice.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Sattya
 * create at 9/20/2024 1:20 PM
 */
@Configuration
public class SecurityConfig {
    private final String[] freeResourceUrls = {"/docs/**","/swagger-ui/**","/api-docs/**","/v3/api-docs/**","/aggregate/**"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity.authorizeHttpRequests(authorize ->
                      authorize
                              .requestMatchers(freeResourceUrls).permitAll()
                              .anyRequest().authenticated())
                        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                        .build();

    }
}
