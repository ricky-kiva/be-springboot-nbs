package com.rickyslash.nbs.domain.appuser;

import com.rickyslash.nbs.domain.appuser.model.AppUser;
import com.rickyslash.nbs.domain.appuser.services.CreateNewAppUserSvc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
  private final CreateNewAppUserSvc createNewAppUserSvc;

  public AppUserController(CreateNewAppUserSvc createNewAppUserSvc) {
    this.createNewAppUserSvc = createNewAppUserSvc;
  }

  @PostMapping("/register")
  public ResponseEntity<String> createNewUser(@RequestBody AppUser user) {
    return createNewAppUserSvc.execute(user);
  }
}
