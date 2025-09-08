package com.rickyslash.nbs.infrastructure.security.services;

import com.rickyslash.nbs.domain.appuser.AppUserRepository;
import com.rickyslash.nbs.domain.appuser.model.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsSvc implements UserDetailsService {
  private final AppUserRepository appUserRepository;

  public UserDetailsSvc(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<AppUser> optionalUser = appUserRepository.findById(username);

    if (optionalUser.isPresent()) {
      AppUser user = optionalUser.get();

      // HERE to add roles & authorities

      return User
          .withUsername(user.getUsername())
          .password(user.getPassword())
          .build();
    }

    throw new UsernameNotFoundException(username);
  }
}
