# spring boot redis

## Redis Server
### linux redis 설치.
```shell
wget http://download.redis.io/redis-stable.tar.gz
tar xvzf redis-stable.tar.gz
cd redis-stable
make

## redis-server 실행
> redis-server

## redis-cli 실행
> redis-cli
```

### redis.conf 설정
- 일반적으로 캐시서버 운용시, 하나의 단일 캐시서버만 운용하지 않는다.
  - 마스터와 슬레이브 서버가 있으며, 마스터 서버에 대한 내용을 이중화 시켜놓은 슬레이브 서버가 있다.
  - 그에 따른 ```redis.conf``` 파일에 대한 설정이 필요하다.

```shell
[master]
bind 127.0.0.1
port 6379
requirepass {pass}
daemonize yes

[slave]
bind 127.0.0.1
port 6380  
replicaof {master ip} {master port}
## ex) replicaof 127.0.0.1 6379
masterauth {pass}
daemonize yes
```

- redis-cli 실행 이후 명령어를 입력하면 ```NOAUTH Authentication required``` 문구가 나옴. 이는 신원확인이 안되었고 .conf 파일에서 requirepass 가 설정되어있기 때문에 나온다. 이를 입력하면 이후에 OK 가 나온다.
- redis-cli 를 통해 publisher 와 subscriber 를 설정할 수 있다.
  - 하나의 채널에서 publisher 는 메시지를 발행하고, subscriber 는 메시지를 구독하고 있다.
  - subscriber 는 publisher 과 누구에 관계없이, 채널에 구독을 요청하고 채널에 들어온 모든 메시지를 읽는다.



## Redis Client
### @RedisHash
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

    /** 생략 **/
}
```

# REF
- https://redis.io/topics/quickstart
- https://coding-start.tistory.com/130?category=791662
