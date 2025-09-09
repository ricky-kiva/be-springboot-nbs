package com.rickyslash.nbs.domain.appuser.services;

import com.rickyslash.nbs.common.contract.Query;
import com.rickyslash.nbs.domain.appuser.model.AppUser;
import com.rickyslash.nbs.infrastructure.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class LoginAppUserSvc implements Query<AppUser, String> {
  private final AuthenticationManager authenticationManager;

  public LoginAppUserSvc(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public ResponseEntity<String> execute(AppUser user) {
    UsernamePasswordAuthenticationToken springSecToken = new UsernamePasswordAuthenticationToken(
        user.getUsername(),
        user.getPassword()
    );

    try {
      Authentication authentication = authenticationManager.authenticate(springSecToken);

      SecurityContextHolder.getContext().setAuthentication(authentication);

      User userPrincipal = (User) authentication.getPrincipal();

      String jwtToken = JwtUtil.generateToken(userPrincipal);

      return ResponseEntity.ok(jwtToken);
    } catch (Exception e) {
      return ResponseEntity.status(401).body("Invalid username or password");
    }
  }
}
