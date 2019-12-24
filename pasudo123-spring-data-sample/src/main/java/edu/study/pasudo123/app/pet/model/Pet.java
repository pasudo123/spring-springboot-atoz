package edu.study.pasudo123.app.pet.model;

import edu.study.pasudo123.app.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "pets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT", nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(30)", length = 30, nullable = false)
    private String name;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT", nullable = false, referencedColumnName = "id")
    private User user;

    @Builder
    public Pet(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
