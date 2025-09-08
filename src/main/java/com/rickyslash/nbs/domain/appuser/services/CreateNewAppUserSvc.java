package com.rickyslash.nbs.domain.appuser.services;

import com.rickyslash.nbs.common.contract.Command;
import com.rickyslash.nbs.domain.appuser.AppUserRepository;
import com.rickyslash.nbs.domain.appuser.model.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateNewAppUserSvc implements Command<AppUser, String> {
  private final AppUserRepository appUserRepository;
  private final PasswordEncoder encoder;

  public CreateNewAppUserSvc(AppUserRepository appUserRepository, PasswordEncoder encoder) {
    this.appUserRepository = appUserRepository;
    this.encoder = encoder;
  }

  @Override
  public ResponseEntity<String> execute(AppUser user) {
    Optional<AppUser> optionalUser = appUserRepository.findById(user.getUsername());

    if (optionalUser.isEmpty()) {
      AppUser newUser = new AppUser();

      newUser.setUsername(user.getUsername());
      newUser.setPassword(encoder.encode(user.getPassword()));

      appUserRepository.save(newUser);

      return ResponseEntity.ok("User created");
    }

    return ResponseEntity.badRequest().body("User already exists");
  }
}
