package com.rickyslash.nbs.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    String ADMIN_USERNAME = "admin";
    String ADMIN_PASSWORD = "1";

    UserDetails admin = User
        .withUsername(ADMIN_USERNAME)
        .password(encoder.encode(ADMIN_PASSWORD))
        .build();

    return new InMemoryUserDetailsManager(admin);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .authorizeHttpRequests(authorize -> {
          authorize.requestMatchers("/open").permitAll();
          authorize.requestMatchers("/closed").authenticated();
        })
        .httpBasic(Customizer.withDefaults())
        .build();
  }
}
