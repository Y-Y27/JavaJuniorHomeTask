package com.yanch.javaDevHomeTask.security;

import com.yanch.javaDevHomeTask.model.Status;
import com.yanch.javaDevHomeTask.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final String isActive;

    public static UserDetails fromUser(UserAccount userAccount) {
        return new User(
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getRole().getGrantedAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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

        if (!this.isActive.equals("ACTIVE")) return false;

        return true;
    }
}
