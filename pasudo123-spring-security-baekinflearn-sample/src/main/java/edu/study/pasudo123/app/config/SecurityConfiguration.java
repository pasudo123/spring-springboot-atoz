package edu.study.pasudo123.app.config;

import edu.study.pasudo123.app.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .mvcMatchers("/", "/info", "/account/**").permitAll()

                /** admin url 은 `ADMIN` 롤이 있을 때 접근 가능 **/
                .mvcMatchers("/admin").hasRole("ADMIN")

                /** 기타 등등의 request 는 인증만 가능하면 접근 가능 **/
                .anyRequest().authenticated();

        http.formLogin();
        http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /** userDetailsService 를 명시 **/
        /** 하지만 userDetails 타입이 빈으로 등록되어있다면 알아서 등록된다. : __주석처리__ 가 가능 **/
        auth.userDetailsService(accountService);
    }

    /**
     *  인메모리 방식
     */
    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        /**
//         * {noop} : 암호화를 하지 않음을 명시.
//         */
//
//        auth.inMemoryAuthentication()
//                .withUser("park")
//                .password("{noop}1")
//                .roles("USER")
//                    .and()
//                .withUser("admin")
//                .password("{noop}123")
//                .roles("ADMIN");
//    }
}
