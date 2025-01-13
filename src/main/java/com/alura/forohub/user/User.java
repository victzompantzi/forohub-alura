package com.alura.forohub.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.alura.forohub.authentication.SignUpData;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String userPassword;
  private String email;
  private Boolean statusAccount;

  @ManyToOne
  @JoinColumn(name = "profile_id")
  private Profile profile;

  public User(SignUpData userRegistrationData) {
    this.username = userRegistrationData.username();
    this.userPassword = userRegistrationData.password();
    this.email = userRegistrationData.email();
    this.statusAccount = true;
    this.profile = userRegistrationData.profile();
  }

  public void updateData(UserUpdateData userUpdateData) {
    if (userUpdateData.password() != null) {
      this.userPassword = userUpdateData.password();
    }
    if (userUpdateData.email() != null) {
      this.email = userUpdateData.email();
    }
    if (userUpdateData.profile() != null) {
      this.profile = userUpdateData.profile();
    }
  }

  public void deactivateUser() {
    this.statusAccount = false;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {
    return userPassword;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
