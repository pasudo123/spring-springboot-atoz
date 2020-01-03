package edu.study.pasudo123.app.account.controller;

import edu.study.pasudo123.app.account.repository.Account;
import edu.study.pasudo123.app.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account) {

        /** modelAttribute 시 setter 가 필요. **/
        return accountService.createNew(account);
    }
}
