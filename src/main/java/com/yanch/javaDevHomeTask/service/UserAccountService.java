package com.yanch.javaDevHomeTask.service;

import com.yanch.javaDevHomeTask.exception.UsernameAlreadyExistException;
import com.yanch.javaDevHomeTask.model.Role;
import com.yanch.javaDevHomeTask.model.Status;
import com.yanch.javaDevHomeTask.model.UserAccount;
import com.yanch.javaDevHomeTask.repository.UserAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAccountService(UserAccountRepository userAccountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void registerNewAccount(UserAccount userFromForm) throws UsernameAlreadyExistException {
        if (usernameExists(userFromForm.getUsername())) {
            throw new UsernameAlreadyExistException("Username " + userFromForm.getUsername() + " already exists");
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userFromForm.getUsername());
        userAccount.setPassword(bCryptPasswordEncoder.encode(userFromForm.getPassword()));
        userAccount.setFirstName(userFromForm.getFirstName());
        userAccount.setLastName(userFromForm.getLastName());
        userAccount.setRole(Role.USER);
        userAccount.setStatus(Status.ACTIVE);
        userAccount.setCreatedAt(LocalDateTime.now());
        userAccountRepository.save(userAccount);
    }

    private boolean usernameExists(String username) {
        return userAccountRepository.findByUsername(username).isPresent();
    }

    public UserAccount findById(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id: " + id + " not found"));
    }


    @Transactional
    public void updateUserForm(UserAccount userAccount) {
        userAccountRepository.updateUsername(
                userAccount.getId(),
                userAccount.getUsername(),
                userAccount.getFirstName(),
                userAccount.getLastName(),
                userAccount.getRole(),
                userAccount.getStatus());
    }

    public Page<UserAccount> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userAccountRepository.findAll(pageable);
    }

    public void deleteById(long id) {
        userAccountRepository.deleteById(id);
    }


}
