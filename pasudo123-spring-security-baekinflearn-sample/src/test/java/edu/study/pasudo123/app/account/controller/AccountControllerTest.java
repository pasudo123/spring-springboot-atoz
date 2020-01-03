package edu.study.pasudo123.app.account.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc // mvc 테스트 외에 모든 설정을 올린다.
@DisplayName("계정 컨트롤러 테스트")
class AccountControllerTest {

    @Autowired private MockMvc mvc;

    @Test
    @DisplayName("익명사용자의 접근")
    public void indexAnonymous() throws Exception {

        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("익명사용자의 명시적 접근")
    public void indexExplicitAnonymous() throws Exception {

        mvc.perform(get("/").with(anonymous()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("일반사용자의 접근")
    public void indexUser() throws Exception {

        mvc.perform(get("/").with(user("park").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("어드민의 접근")
    public void indexAdmin() throws Exception {

        mvc.perform(get("/").with(user("admin").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("어드민 페이지에 일반 사용자의 접근")
    public void indexAdminByUser() throws Exception {

        mvc.perform(get("/admin").with(user("park").roles("USER")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("어드민 페이지에 어드민의 접근")
    public void indexAdminByAdmin() throws Exception {

        mvc.perform(get("/admin").with(user("admin").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
