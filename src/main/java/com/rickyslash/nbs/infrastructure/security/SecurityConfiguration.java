package com.rickyslash.nbs.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity
public class SecurityConfiguration {
  String AUTHORITY_ADMIN = "ADMIN";
  String AUTHORITY_USER = "USER";
  String ROLE_SUPERUSER = "superuser";
  String ROLE_BASIC_USER = "basicuser";

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    String ADMIN_USERNAME = "admin";
    String ADMIN_PASSWORD = "1";
    String USER_USERNAME = "user";
    String USER_PASSWORD = "2";
    String SUPERUSER_USERNAME = "rickyslash";
    String SUPERUSER_PASSWORD = "99";

    UserDetails admin = User
        .withUsername(ADMIN_USERNAME)
        .authorities(AUTHORITY_ADMIN, AUTHORITY_USER, roleToAuthority(ROLE_BASIC_USER))
        .password(encoder.encode(ADMIN_PASSWORD))
        .build();

    UserDetails user = User
        .withUsername(USER_USERNAME)
        .authorities(AUTHORITY_USER, roleToAuthority(ROLE_BASIC_USER))
        .password(encoder.encode(USER_PASSWORD))
        .build();

    UserDetails superuser = User
        .withUsername(SUPERUSER_USERNAME)
        .authorities(AUTHORITY_ADMIN, AUTHORITY_USER, roleToAuthority(ROLE_SUPERUSER))
        .password(encoder.encode(SUPERUSER_PASSWORD))
        .build();

    return new InMemoryUserDetailsManager(admin, user, superuser);
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
          authorize.requestMatchers(HttpMethod.POST, "/product").authenticated();
          authorize.requestMatchers(HttpMethod.GET, "/admin-dashboard").hasAuthority(AUTHORITY_ADMIN);
          authorize.requestMatchers(HttpMethod.GET, "/user-dashboard")
              .hasAnyAuthority(AUTHORITY_USER, AUTHORITY_ADMIN);

          authorize.anyRequest().authenticated();
        })
        .httpBasic(Customizer.withDefaults())
        .build();
  }

  private String roleToAuthority(String role) {
    return "ROLE_" + role;
  }
}
