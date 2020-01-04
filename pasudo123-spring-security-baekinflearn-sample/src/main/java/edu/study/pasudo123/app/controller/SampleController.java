package edu.study.pasudo123.app.controller;

import edu.study.pasudo123.app.account.repository.AccountContext;
import edu.study.pasudo123.app.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final AccountRepository accountRepository;

    @GetMapping
    public String root() {
        return "root";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal) {

        AccountContext.setAccount(
                accountRepository.findByUsername(principal.getName())
                        .orElse(null));

        return "dashboard";
    }
}
