package edu.study.pasudo123.app.user.model;

import edu.study.pasudo123.app.member.model.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user", indexes = {
        @Index(name = "age_idx", columnList = "age")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    public enum Gender{
        MAN, WOMAN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "bigint(20)")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(30)")
    private String name;

    @Column(name = "age", nullable = false, columnDefinition = "tinyint(3)")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, columnDefinition = "ENUM('WOMAN', 'MAN')")
    private User.Gender gender;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false, columnDefinition = "datetime")
    private LocalDateTime regDate;

    @Builder
    public User(String name, Integer age, User.Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

}
