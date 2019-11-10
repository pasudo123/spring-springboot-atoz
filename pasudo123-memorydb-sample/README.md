# spring boot redis

## @RedisHash
- 특정 엔티티를 레디스 엔티티로 명시
- 저장하는 데이터가 많아지면 많이질수록, 레디스 엔티티는 이후에 무수히 많아질 수 있다.
  - 그렇기 때문에, 이러한 엔티티들을 보관하는 하나의 해쉬키 값을 설정해두는데 그게 바로 ```@RedisHash("Student")``` 이다.
> 해당 엔티티가 레디스에 적재될때 @RedisHash 의 인수를 키 값으로 받아서 해당 엔티티가 적재되는 것이다.

```java
@RedisHash("Student")
@Getter
@ToString
public class Student implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }

    private String id;

    private String name;

    private Gender gender;

    private int grade;

    @Builder
    public Student(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    public void updateName(final String name) {
        this.name = name;
    }

    public void updateGrade(final int grade) {
        this.grade = grade;
    }
}
```
