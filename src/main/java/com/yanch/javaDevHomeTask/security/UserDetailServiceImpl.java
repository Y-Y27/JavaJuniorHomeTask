package com.yanch.javaDevHomeTask.security;

import com.yanch.javaDevHomeTask.model.UserAccount;
import com.yanch.javaDevHomeTask.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service ("userDetailsServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public UserDetailServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User account with name: " + username + "does not found"));
        return SecurityUser.fromUser(userAccount);
    }
}
