package dev.practice.moneymanagementback.config;

import dev.practice.moneymanagementback.security.JWTAuthenticationEntryPoint;
import dev.practice.moneymanagementback.security.JWTAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JWTAuthenticationEntryPoint authenticationEntryPoint;
    private JWTAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(HttpMethod.GET,"/api/v1/**").permitAll();
            authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
            authorize.requestMatchers(HttpMethod.POST,"/api/v1/accounts/**").hasAnyRole("ADMIN", "USER");
            authorize.requestMatchers(HttpMethod.POST,"/api/v1/incomes/accounts/**").hasAnyRole("ADMIN", "USER");
            authorize.requestMatchers(HttpMethod.POST,"/api/v1/expenses/accounts/**").hasAnyRole("ADMIN", "USER");
            authorize.requestMatchers(HttpMethod.DELETE,"/api/v1/accounts/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/auth/**").permitAll();
        }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
