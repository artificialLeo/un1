package com.foxminded.university.Security;

import com.foxminded.university.Models.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login*").permitAll()
                .requestMatchers("/register*").permitAll()
                .requestMatchers("/reg*").permitAll()
                .requestMatchers("/student-route*").hasAnyAuthority(Roles.STUDENT.getRoleName())
                .requestMatchers("/student*").hasAnyAuthority(Roles.ADMIN.getRoleName(), Roles.TEACHER.getRoleName())
                .requestMatchers("/subject*").hasAnyAuthority(Roles.ADMIN.getRoleName(), Roles.TEACHER.getRoleName(), Roles.STUDENT.getRoleName())
                .requestMatchers("/teacher-route*").hasAnyAuthority(Roles.TEACHER.getRoleName())
                .requestMatchers("/teacher*").hasAnyAuthority(Roles.ADMIN.getRoleName(), Roles.STUDENT.getRoleName())
                .requestMatchers("/timetable*").hasAnyAuthority(Roles.ADMIN.getRoleName(), Roles.TEACHER.getRoleName(), Roles.STUDENT.getRoleName())
                .requestMatchers("/university*").hasAnyAuthority(Roles.ADMIN.getRoleName(), Roles.TEACHER.getRoleName())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    if (authentication.getAuthorities().stream()
                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Roles.ADMIN.getRoleName()))) {
                        response.sendRedirect("/admin-route");
                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Roles.TEACHER.getRoleName()))) {
                        response.sendRedirect("/teacher-route");
                    } else {
                        response.sendRedirect("/student-route");
                    }
                })
                .and()
                .logout()
                .deleteCookies("JSESSIONID");
        return http.build();
    }
}
