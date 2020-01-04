package edu.study.pasudo123.app.account.controller;

import edu.study.pasudo123.app.account.repository.Account;
import edu.study.pasudo123.app.account.repository.AccountRepository;
import edu.study.pasudo123.app.account.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Mock 애노테이션에 대한 계정 컨트롤러 테스트")
public class AccountControllerTestByAnnotation {

    @Autowired private MockMvc mvc;
    @Autowired private AccountService accountService;

    @Test
    @WithAnonymousUser
    @DisplayName("익명사용자의 접근")
    public void indexAnonymous() throws Exception {

        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "PARK", roles = "USER")
    @DisplayName("일반사용자의 접근")
    public void indexUser() throws Exception {

        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("어드민의 접근")
    public void indexAdmin() throws Exception {

        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "PARK", roles = "USER")
    @DisplayName("어드민 페이지에 일반 사용자의 접근")
    public void indexAdminByUser() throws Exception {

        mvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    @DisplayName("폼 로그인 인증 성공한다.")
    public void loginTest() throws Exception {

        final String username = "PARK";
        final String password = "1234";
        final Account account = this.createAccount(username, password);

        mvc.perform(formLogin()
                    .user(username)
                    .password(password))
                .andExpect(authenticated());
    }

    @Test
    @Transactional
    @DisplayName("폼 로그인 인증 실패한다.")
    public void loginFailTest() throws Exception {

        final String username = "PARK";
        final String password = "1234";
        final Account account = this.createAccount(username, password);

        mvc.perform(formLogin()
                    .user(username)
                    .password("0000"))
            .andExpect(unauthenticated());
    }

    private Account createAccount(final String username,final String password) {

        final Account account = Account
                .builder()
                .username(username)
                .password(password)
                .role("USER")
                .build();

        return accountService.createNew(account);
    }
}