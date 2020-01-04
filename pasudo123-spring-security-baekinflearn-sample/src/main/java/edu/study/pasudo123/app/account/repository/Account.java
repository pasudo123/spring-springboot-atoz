package edu.study.pasudo123.app.account.repository;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    @Builder
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void encodePassword(final PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
