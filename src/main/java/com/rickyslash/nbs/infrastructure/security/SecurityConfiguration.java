package com.rickyslash.nbs.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
  String AUTHORITY_ADMIN = "ADMIN";
  String AUTHORITY_USER = "USER";

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    String ADMIN_USERNAME = "admin";
    String ADMIN_PASSWORD = "1";
    String USER_USERNAME = "user";
    String USER_PASSWORD = "2";


    UserDetails admin = User
        .withUsername(ADMIN_USERNAME)
        .authorities(AUTHORITY_ADMIN, AUTHORITY_USER)
        .password(encoder.encode(ADMIN_PASSWORD))
        .build();

    UserDetails user = User
        .withUsername(USER_USERNAME)
        .authorities(AUTHORITY_USER)
        .password(encoder.encode(USER_PASSWORD))
        .build();

    return new InMemoryUserDetailsManager(admin, user);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> {
          authorize.requestMatchers("/open").permitAll();
          authorize.requestMatchers("/closed").authenticated();
          authorize.requestMatchers(HttpMethod.POST, "/product").authenticated();
          authorize.requestMatchers(HttpMethod.GET, "/admin-dashboard").hasAuthority(AUTHORITY_ADMIN);
          authorize.requestMatchers(HttpMethod.GET, "/user-dashboard")
              .hasAnyAuthority(AUTHORITY_USER, AUTHORITY_ADMIN);
        })
        .httpBasic(Customizer.withDefaults())
        .build();
  }
}
