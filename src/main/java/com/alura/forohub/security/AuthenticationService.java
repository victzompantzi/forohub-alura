package com.alura.forohub.security;

import com.alura.forohub.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  // Dependency injection through the constructor
  // public AuthenticationService(UserRepository userRepository) {
  //   this.userRepository = userRepository;
  // }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }
}
