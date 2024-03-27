package com.diego.curso.springboot.app.springbootcrud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.diego.curso.springboot.app.springbootcrud.security.filter.JwtAuthenticationFilter;
import com.diego.curso.springboot.app.springbootcrud.security.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {

  @Autowired
  private AuthenticationConfiguration authenticationConfiguration;

  @Bean
  AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests((authz) -> authz
        .requestMatchers(HttpMethod.GET, "/api/users").permitAll() // mostrar users para todos los roles
        .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll() // crear user para todos los roles
        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN") // crear user con rol de admin si solo tienen
                                                                         // el rol de ADMIN
        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")// crear productos solo si tienen el rol de
                                                                           // ADMIN
        .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN") // modificar productos solo si tienen el
                                                                                // rol de ADMIN
        .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN") // eliminar productos solo si tienen
                                                                                   // rol de ADMIN
        .anyRequest().authenticated())
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))
        .addFilter(new JwtValidationFilter(authenticationManager()))
        .csrf(config -> config.disable())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

}
