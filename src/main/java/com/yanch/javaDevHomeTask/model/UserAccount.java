package com.yanch.javaDevHomeTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @Pattern(regexp = "^[a-zA-Z]{3,16}", message = "Только латинские символы. От 3 до 16 символов")
    private String username;

    @Column(name = "password")
    @Pattern(regexp = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{3,16}$",
            message = "Только латинские символы , мин. один символ, мин. одна цифра, макс 16 символов")
    private String password;

    @Column(name = "firstname")
    @Pattern(regexp = "^[a-zA-Z]{1,16}", message = "Только латинские символы. От 1 до 16 символов")
    private String firstName;

    @Column(name = "lastname")
    @Pattern(regexp = "[a-zA-Z]{1,16}", message = "Только латинские символы. От 1 до 16 символов")
    private String lastName;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "createdat")
    private LocalDateTime createdAt;
}
