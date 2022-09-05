# study-spring5

## 참고문헌
* 전문가를 위한 Spring5 개정판, 길벗출판사

## Environments
* SDK : Azul zulu version 13.0.12
* Gradle : 6.2


### Chapter 02.
* HelloWorldDecoupled : 수정하기 쉬운 유연한 메커니즘 구현
* HelloWorldDecoupledWithFactory : 가변적인 내용을 외부에서 관리하고 런타임에 명령줄 인자로부터 메시지 내용을 받아오는 것.
* HelloWorldSpringDI : Spring Context 에 Bean 을 정의, 의존성을 주입하여 Context로부터 Bean 획득
* HelloWorldSpringAnnotated : 빈 정의를 담는 @Configuration 애터테이션이 붙은 클래스를 이용해 의존성을 주입. 

### Chapter 03.
* IoC 개념
  - Dependency Injection
  > IoC 컨테이너가 컴포넌트에 의존성을 주입. 
  > + Constructor DI : 컴포넌트의 생성자를 이용해 해당 컴포넌트가 필요로 하는 의존성을 주입. 
  > + Setter DI : 자바빈 방식의 수정자 메서드를 이용해 컴포넌트 의존성을 주입. 
  - Dependency Lookup
  > 컴포넌트 스스로 의존성의 참조를 가져와야 하는 방식. 
  > + Dependency Pool : 필요시 레지스트리로부터 의존성을 가져오는 방식. 구 EJB 등 
  > + Contextualized Dependency Lookup : 자원을 관리하는 컨테이너로부터 의존성을 가져오는 방식. 
* Spring IoC
* Spring DI
* Spring Application Context
