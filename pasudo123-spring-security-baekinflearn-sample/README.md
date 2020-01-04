# 스프링 시큐리티 백기선님 강의 정리.
## SecurityContextHolder & Authentication

- __SecurityContextHolder__
  - SecurityContext 를 제공하며, `ThreadLocal` 을 사용한다.
  - `ThreadLocal` 은 한 스레드 내에서 공유하는 저장소이다.
  
- __SecurityContext__
  - SecurityContextHolder 안에 담겨져 관리된다.
  
- __Authentication__
  - SecurityContext 안에 담겨져 관리된다.
  - 한 스레드내에서 공유된다.
  
- __Principal__
  - Authentication 안에 담겨져 관리된다.
  - `누구` 인지에 대한 정보.
  - __UserDetailsService__ 에서 리턴한 `객체`

- __Grant Authority__
  - 인증 이후에 `인가 및 권한` 을 확인할 때 해당 정보를 참조.

- __UserDetails__
  - 애플리케이션이 가지고 있는 유저정보와 스프링 시큐리티가 사용하는 `Authentication` 객체 사이의 어댑터.
  
- __UserDetailsService__
  - 유저 정보를 UserDetails 타입으로 가져오는 DAO 인터페이스
  - 내부 구현은 개발자가 직접 구현하면 된다.

## AuthenticationManager & Authentication
스프링 시큐리티에서 인증(Authentication) 은 `AuthenticationManager` 가 수행한다.   
- __인자로 받은 Authentication 이 유효한 인증인지 여부를 확인하고 Authentication 객체를 리턴__
- 인증을 확인하는 과정에서 비활성 계정, 잘못된 비밀번호, 잠긴 계정 등에 대한 에러를 처리할 수 있다.
- provider manager 를 확인한다.

## ThreadLocal
- java.lang 에서 기본으로 제공하는 쓰레드 범위 변수. (scope 이 스레드 내로 제한적)
- 스레드 수준의 데이터 저장소, 같은 스레드 내에서 공유
  - 같은 스레드라면 해당 데이터를 메소드 매개변수로 넘겨줄 필요가 없다.
- SecurityContextHolder 기본 전략. SecurityContextHolder 를 `ThreadLocal` 에 저장한다.

## 궁금증
- 해당 request 가 동일한 같은 Thread 를 was 는 어떻게 판단할까?
