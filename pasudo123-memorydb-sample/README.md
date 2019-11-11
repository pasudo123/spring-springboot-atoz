# spring boot redis

## Redis
- 고성능 key/value 저장소로서 리스트, 해시, 셋, 정렬된 셋 등 여러 형식의 자료구조를 지원하는 오픈소스 in-memory Data Store 이다. (NoSQL)
- pub/sub 을 통한 메시지 브로커 기능도 지원한다.
- Redis Sentinel, Redis Cluster 를 통해 고 가용성 및 가졷 파티셔닝을 제공한다.


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

- Redis 를 구성할 때, Redis Single Instance 구성만을 하지 않고 cluster 구성을 해야한다.
> cluster 란, 각기 다른 서버를 하나로 묶어 하나의 시스템처럼 동작하게 함으로써, 클라이언트에게 고가용성을 제공하는 것을 의미한다. 특징으로 여러 데이터를 여러 대의 장비에서 처리하므로, 특정 데이터에 집중되는 트래픽을 서버들이 나눠 처리할 수 있다. 또한 서버의 일부분에 장애가 일어나더라도 다른 서버의 보완을 통해 서비스를 게속 이어나갈 수 있고, 데이터 유실도 최소화할 수 있다.


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

## Redis Pub/Sub
-  Redis 는 pub/sub 을 지원한다.
  - pub/sub 은 우리가 흔히 아는 메시지 큐와는 특성이 다르다.
  - pub/sub 은 현재 채널을 구독 중인, subscriber 들 모두에게 특정한 이벤트를 전달하는 것이다.
  - 메시지 큐는 보통 일반적으로 작업을 queueing 하고 있다가, 요청하는 곳에 데이터를 전달하기 위해서 __데이터를 보관하는 시스템__ 이다.
  - 다시 말해서, redis 에서 어느 클라이언트도 채널을 구독하고 있지 않다면, subscribe 하고 있지 않다면 publish 한 데이터들은 모두 유실된다.
> Redis pub/sub 과 message queue 는 엄연히 다르다.


# REF
- https://redis.io/topics/quickstart
- https://coding-start.tistory.com/130?category=791662
- https://daddyprogrammer.org/post/1601/redis-cluster/
