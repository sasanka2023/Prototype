package com.example.CarrerLink_backend.config;


import com.example.CarrerLink_backend.filter.JWTFilter;
import com.example.CarrerLink_backend.repo.UserRepo;
import com.example.CarrerLink_backend.service.impl.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepo userRepo;
    private final JWTFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                    .csrf(c->c.disable())
                    .sessionManagement(s->s.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(r->r.
                             requestMatchers("/api/auth/login","/api/auth/register/company","/api/auth/register/student")
                            .permitAll()
                            .requestMatchers("/api/companies/**").hasRole("COMPANY") // Accessible by Student role
                            .requestMatchers("/api/students/**").hasRole("STUDENT") // Accessible by Company role
                            .requestMatchers("/api/cv/**", "/api/jobs/**","/api/v1/requiredCourses/**").authenticated()
                             // Any other endpoints require authentication
                    .anyRequest().authenticated())
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class
                    )
                    .authenticationProvider(authenticationProvider())
                    .httpBasic(Customizer.withDefaults())

                    .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService(userRepo);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
