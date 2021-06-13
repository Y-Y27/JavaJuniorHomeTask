package com.yanch.javaDevHomeTask.repository;

import com.yanch.javaDevHomeTask.model.Role;
import com.yanch.javaDevHomeTask.model.Status;
import com.yanch.javaDevHomeTask.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);

    @Modifying
    @Query("update UserAccount u set u.username = :username," +
            "u.firstName = :firstName, " +
            "u.lastName = :lastName , " +
            "u.role = :role ," +
            "u.status = :status where u.id = :id")
    void updateUsername(@Param(value = "id") long id,
                        @Param(value = "username") String username,
                        @Param(value = "firstName") String firstName,
                        @Param(value = "lastName") String lastName,
                        @Param(value = "role") Role role,
                        @Param(value = "status") Status status);
}
