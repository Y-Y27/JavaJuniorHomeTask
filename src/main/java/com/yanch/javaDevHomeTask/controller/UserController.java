package com.yanch.javaDevHomeTask.controller;

import com.yanch.javaDevHomeTask.exception.UsernameAlreadyExistException;
import com.yanch.javaDevHomeTask.model.UserAccount;
import com.yanch.javaDevHomeTask.service.UserAccountService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserAccountService userAccountService;

    public UserController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String success(Model model) {
        return findPaginated(1, "username", "asc", model);
    }


    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('userAccount:read')")
    public String findUserAccountById(@PathVariable Long id, Model model) {
        UserAccount userAccount = userAccountService.findById(id);
        model.addAttribute("userAccount", userAccount);
        return "userId";
    }

    @GetMapping("/user/new")
    public String getRegistrationForm(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "registrationForm";
    }

    @PostMapping("/user/new")
    @PreAuthorize("hasAuthority('userAccount:write')")
    public String createNewUserAccount(@ModelAttribute("userAccount") @Valid UserAccount userAccount,
                                       BindingResult bindingResult,
                                       Model model) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }

        try {
            userAccountService.registerNewAccount(userAccount);
        } catch (UsernameAlreadyExistException e) {
            e.printStackTrace();
        }
        return "redirect:/user";
    }

    @GetMapping("/user/{id}/edit")
    @PreAuthorize("hasAuthority('userAccount:write')")
    public String editUserAccount(@PathVariable Long id, Model model) {
        UserAccount userAccount = userAccountService.findById(id);
        model.addAttribute("userAccount", userAccount);
        return "userEditForm";
    }

    @PostMapping("/user/{id}/edit")
    @PreAuthorize("hasAuthority('userAccount:write')")
    public String updateUserAccount(@PathVariable long id,
                                    @Valid @ModelAttribute("userAccount") UserAccount userAccount,
                                    BindingResult bindingResult,
                                    Model model) {

        if (bindingResult.hasErrors()) {
            return "userEditForm";
        }

        userAccountService.updateUserForm(userAccount);
        return "redirect:/user";
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('userAccount:read')")
    public String logout() {
        return "login";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<UserAccount> page = userAccountService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<UserAccount> userAccounts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("userAccounts", userAccounts);
        return "usersList";
    }

    @PostMapping("/user-delete/{id}")
    @PreAuthorize("hasAuthority('userAccount:write')")
    public String deleteUserAccount(@PathVariable long id) {
        userAccountService.deleteById(id);
        return "redirect:/user";
    }
}
