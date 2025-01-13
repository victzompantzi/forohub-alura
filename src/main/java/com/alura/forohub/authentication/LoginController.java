package com.alura.forohub.authentication;

import com.alura.forohub.security.JwtData;
import com.alura.forohub.security.TokenService;
import com.alura.forohub.user.User;
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
public class LoginController {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private TokenService tokenService;

  @PostMapping
  public ResponseEntity autenticarUsuario(
      @RequestBody @Valid LoginData userAuthenticationData) {
    Authentication authToken =
        new UsernamePasswordAuthenticationToken(
            userAuthenticationData.username(), userAuthenticationData.userPassword());
    var authenticatedUser = authenticationManager.authenticate(authToken);
    var jwtToken = tokenService.generarToken((User) authenticatedUser.getPrincipal());
    return ResponseEntity.ok(new JwtData(jwtToken));
  }
}
