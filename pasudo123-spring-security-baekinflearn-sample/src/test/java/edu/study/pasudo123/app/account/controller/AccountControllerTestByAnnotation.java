package edu.study.pasudo123.app.account.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Mock 애노테이션에 대한 계정 컨트롤러 테스트")
public class AccountControllerTestByAnnotation {

    @Autowired private MockMvc mvc;

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
}
