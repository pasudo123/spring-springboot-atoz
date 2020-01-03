package edu.study.pasudo123.app.account.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    public void encodePassword(final PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
