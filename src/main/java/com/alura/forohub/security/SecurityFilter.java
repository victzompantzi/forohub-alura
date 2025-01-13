package com.alura.forohub.security;

import com.alura.forohub.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UserRepository userRepository;

  public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var authHeader = getHeader(request);
    try {
      if (authHeader != null) {
        var subject = tokenService.getSubject(authHeader);
        if (subject != null) {
          var usuario = userRepository.findByUsername(subject);
          if (usuario != null) {
            var authentication =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }
      }
      filterChain.doFilter(request, response);
    } catch (RuntimeException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Invalid or expired token");
    }
  }

  // Get 'Authorization' header validation
  private String getHeader(HttpServletRequest request) {
    var header = request.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      return null;
    }
    return header.replace("Bearer ", "");
  }
}
