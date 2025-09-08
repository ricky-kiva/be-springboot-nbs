package com.rickyslash.nbs.infrastructure.security.example;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
  @GetMapping("/open")
  public String open() {
    return "OPEN";
  }

  @GetMapping("/closed")
  public String closed() {
    return "CLOSED";
  }

  @GetMapping("/admin-dashboard")
  public String adminDashboard() {
    return "HELLO ADMIN";
  }

  @GetMapping("/user-dashboard")
  public String userDashboard() {
    return "HELLO USER";
  }

  @PreAuthorize("hasRole('superuser')")
  @GetMapping("/terminal")
  public String terminal() {
    return "IN TERMINAL";
  }

  @PreAuthorize("hasRole('basicuser') or hasRole('superuser')")
  @GetMapping("/cloud-services")
  public String cloudServices() {
    return "IN CLOUD SERVICES DASHBOARD";
  }
}
