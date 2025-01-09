package com.example.alura.forohub.controller;

import com.example.alura.forohub.domain.user.User;
import com.example.alura.forohub.domain.user.UserAuthenticationData;
import com.example.alura.forohub.infra.security.JwtData;
import com.example.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity autenticarUsuario(
      @RequestBody @Valid UserAuthenticationData userAuthenticationData) {
    Authentication authToken = new UsernamePasswordAuthenticationToken(
        userAuthenticationData.username(), userAuthenticationData.password());
    var authenticatedUser = authenticationManager.authenticate(authToken);
    var jwtToken = tokenService.generarToken((User) authenticatedUser.getPrincipal());
    return ResponseEntity.ok(new JwtData(jwtToken));
  }

}
