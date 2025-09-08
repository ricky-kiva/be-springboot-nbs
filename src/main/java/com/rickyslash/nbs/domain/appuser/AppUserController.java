package com.rickyslash.nbs.domain.appuser;

import com.rickyslash.nbs.domain.appuser.model.AppUser;
import com.rickyslash.nbs.domain.appuser.services.CreateNewAppUserSvc;
import com.rickyslash.nbs.domain.appuser.services.LoginAppUserSvc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
  private final CreateNewAppUserSvc createNewAppUserSvc;
  private final LoginAppUserSvc loginAppUserSvc;

  public AppUserController(CreateNewAppUserSvc createNewAppUserSvc, LoginAppUserSvc loginAppUserSvc) {
    this.createNewAppUserSvc = createNewAppUserSvc;
    this.loginAppUserSvc = loginAppUserSvc;
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody AppUser user) {
    return loginAppUserSvc.execute(user);
  }

  @PostMapping("/register")
  public ResponseEntity<String> createNewUser(@RequestBody AppUser user) {
    return createNewAppUserSvc.execute(user);
  }
}
