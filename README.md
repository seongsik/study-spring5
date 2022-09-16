study-spring5
=============

## 참고문헌
* 전문가를 위한 Spring5 개정판, 길벗출판사

## Environments
* Framework : Spring 5.0
* SDK : Azul zulu version 13.0.12
* Gradle : 6.2

------------------------------------------------------------------
## Spring IoC, DI
### IoC 
* Dependency Injection
  * IoC 컨테이너가 컴포넌트에 의존성을 주입. 
  * Constructor DI : 컴포넌트의 생성자를 이용해 해당 컴포넌트가 필요로 하는 의존성을 주입. 컴포넌트 사용 전에 해당 컴포넌트의 의존성을 반드시 가지고 있어야 할 때 유용하며 불변 객체로 사용도 가능함. 
  * Setter DI : 자바빈 방식의 수정자 메서드를 이용해 컴포넌트 의존성을 주입. 의존성을 노출하기는 하지만 자유롭게 주입하여 사용 가능함.
* Dependency Lookup
  * 컴포넌트 스스로 의존성의 참조를 가져와야 하는 방식. 
  * Dependency Pool : 필요시 레지스트리로부터 의존성을 가져오는 방식. 구 EJB 등 
  * Contextualized Dependency Lookup : 자원을 관리하는 컨테이너로부터 의존성을 가져오는 방식.

### Spring Application Context
* BeanFactory 를 상속한 interface. 
* DI, AOP, 국제화, 이벤트 처리 등의 서비스를 제공. 
* 직접 코드로 부트스트랩하거나 ContextLoaderListener 를 이용해 부트스트랩. 
* 일반적으로 XML(or properties)에 Application Infra(Datasource, Transaction Manager, JMS, JMX 등) 을 정의하고, 애너테이션으로 DI구성을 정의하는 방식 선호 

### component-scan
* 지정한 패키지 하위의 클래스에 선언된 애너테이션과 Bean 을 스캔하도록 스프링에 지시한다. 
* 또는 구성 클래스를 이용해서 애너테이션 Bean 정의를 읽을 수 있다. 
```xml
<context:component-scan base-package="com.sik.study.spring5.ch03.annotated">
</context:component-scan>
```
```java
@ComponentScan(basePackages = {"com.sik.study.spring5.ch03.annotated"})
@Configuration
public class HelloWorldConfiguration {

}
```

#### Bean Alias Naming
* @Component 애너테이션에 인수를 지정하지 않는 경우 스프링 IoC컨테이너에 의해 식별자 결정
* 클래스명 기준으로 생성하되, 첫 문자를 Lowercase 로 처리. 

#### 빈 생성방식
* 기본적으로 스프링의 모든 Bean은 기본적으로 Singleton 인스턴스. 
* 비 싱글턴 객체로 사용하고자 하는 경우(단위테스트 등) 속성 명시하여 prototype, request 등 생성 소멸 시점을 정의할 수 있음.

#### Autowiring
* byName, byType, Constructor, default, no 중 택1 
* 스프링은 Constructor, byType 방식을 자동으로 선택. 
* 소규모 Application 에서 시간을 절약할 수 있지만, 유연성이 떨어져 권장하지는 않음. 
  * byType의 경우 ApplicationContext 내에 각 타입별로 한 개의 인스턴스만을 가질 수 있음.
  * byName으로 연결하고자 클래스에 인위적인 프로퍼티 이름을 지정하려 함.


------------------------------------------------------------------
## Spring Structure
### Bean Lifecycle Management
* IoC 주요 기능으로, 생성 또는 소멸과 같은 Lifecycle 특정 시점에 통지받을 수 있게 Bean을 생성.
* post-initialization
  * 초기화 이후 이벤트. Bean에 모든 프로퍼티 값을 설정하고 의존성 점검을 마친 직후 발생. 
* pre-destruction
  * 소멸 전 이벤트. 스프링이 Bean 인스턴스를 소멸시키기 직전에 발생. 
  * Prototype Bean의 경우 소멸 전 이벤트를 통지하지 않음.
* Spring 이벤트 수신 메커니즘
  * interface 기반 : 수신을 원하는 통지 유형 interface를 Bean 에 구현 시 콜백을 통한 통지. (InitializingBean, DisposableBean)
  * 메서드 기반 : 초기화/소멸 시 호출할 메서드명을 ApplicationContext 구성에 지정. (init-method, destroy-method)
  * 애너테이션 기반 : 초기화/소멸 시 호출할 메서드에 애너테이션 지정. (JSR-250 표준)
* 표준에 따라 라이프사이클 관리를 직접 구현하는 경우, 이식성을 높일 수 있음. 

### Spring Aware
* Bean 이 ApplicationContext 컨테이너에 접근하게 하는 특정 상황.
* ex) 런타임에 로그를 기록할 때, Bean Name을 알아낼 수 있으면 유용함. 
* 단, Bean Name 에 접근할 수 있다고 해서 Bean 이름에 비즈니스적인 의미를 부여해서는 안 됨.
  * 필요 시 Nameable 구현체 등으로 의존성 주입해 사용. 

### Factory Bean
* new 연산자로는 생성할 수 없는 객체를 관리하기 위한 어댑터로 FactoryBean interface를 제공.
* FactoryBean.getObject() 를 통해 인스턴스 획득

### Java Bean PropertyEditor
* property 값을 원래 자료 타입에서 String 으로 변환하거나, 반대의 작용을 하는 interface.

### MessageSource 
* 스프링의 i18n 국제화 지원 기능으로 MessageSource interface를 사용. 
  * ResourceBundleMessageSource : ResourceBundle 을 이용하여 메시지 로드
  * ReloadableResourceBundleMessageSource : 메시지 소스 파일에서 주기적으로 다시 읽어들일 수 있음. 
  * StaticMessageSource : 상용 애플리케이션 사용 금지. 

### Spring Profile
* 특정 프로파일이 활성화되면 해당 프로파일에 정의된 ApplicationContext 인스턴스만 구성됨.
* 실행 환경에 따라 사용할 프로파일을 다르게 적용할 수 있음.
  * Environment interface를 이용한 구성 
  * JSR-330 애너테이션을 이용한 구성

### Groovy
* Spring 4.0 부터 적용. Groovy Script 에서 Spring ApplicationContext 를 로드할 수 있음. 
* Dependency groovy-all 필요.
* GenericGroovyApplicationContext 를 이용해 접근 가능. 

### Spring boot
* Spring Application 구축 작업을 간소화하는 목적으로 공통 기능을 제공. 
* 적절한 의존성 및 버전이 포함되어 제공되며, XML 기반 구성이 전혀 필요하지 않도록 함.
* build.gradle spring-boot 관련 정의
```groovy
buildscript {
    dependencies {
        classpath "org.springframework.boot:spring-boot-gradle-plugin:2.0.6.RELEASE"
    }
}

apply plugin: 'org.springframework.boot'

dependencies {
	compile "org.springframework.boot:spring-boot-starter:2.0.6.RELEASE"
}
```

#### @SpringBootApplication
* Class Level 에서 사용하도록 설계된 최상위 애너테이션. 
* 다음 애너테이션을 모두 정의한 것과 같은 동작을 한다. 
  * @Configuration : 해당 클래스가 @Bean 으로 빈을 정의하는 구성 클래스임을 정의함. 
  * @EnableAutoConfiguration : 제공된 의존성을 기반으로 필요할 빈을 추측해 구성, ApplicationContext를 활성화. 
  * @ComponentScan : Bean 을 탐색할 최상위 패키지를 지정
* @SpringBootApplication 에 Component Scan 관련 속성을 정의하지 않는 경우. 해당 어트리뷰트를 정의한 클래스의 패키지 하위로 스캔한다.

#### Spring Boot Starter Web
* spring-boot-starter-web 의존성을 적용한다.
```groovy
dependencies {
	compile "org.springframework.boot:spring-boot-starter-web:2.0.6.RELEASE"
}
```
* 기본적으로 Embedded Tomcat 이 실행되며, 상태체크, 환경정보 출력, 측정과 같은 접속 주소가 컨테이너에 등록됨. 
  * /actuator/health : application status 를 JSON 문자열로 안내 
  * /actuator/metrics : Heap Memory 와 Garbage Collection 등 측정 정보를 확인할 수 있음. 



------------------------------------------------------------------
## Spring AOP
* OOP를 보완하는 방향으로 접근. 
* 애플리케이션 여러 영역에서 반복되는 로직(횡단 관심사)를 분리해 효과적인 프로그램 구축. 

### AOP 개념
* Joinpoint : AOP를 사용해 추가 로직을 삽입할 애플리케이션의 특정 지점.
* Advice : 특정 Joinpoint에 실행되는 코드. Joinpoint 전후로 before/after advice로 구분. 
* Pointcut : Advice를 실행하는 조건을 정의. 
* Aspect : 클래스에 캡슐화된 Advice와 Pointcut의 조합. 
* Weaving : Aspect를 애플리케이션 코드에 삽입하는 시점. 
* Target : AOP에 의해 수정된 객체. 
* Introduction : 추가 메서드나 필드를 도입해 객체의 구조를 수정하는 과정. 

### Spring AOP Architecture
* Spring은 런타임 시점에 횡단 관심사를 분석하고 Proxy Bean을 동적으로 생성.
* Proxy Bean은 실행 조건(Joinpoint, Pointcut, Advice)를 분석, 적절한 Advice를 Weaving.
* Spring 에서 제공하는 선언적인 AOP 구성 매커니즘을 사용
  * ProxyFactoryBean : 
    * 스프링 AOP의 위빙과 Proxy 생성 과정을 제어
    * setTarget, setAdvice 적용한 Proxy 객체를 이용해 Spring Advice 를 이용.
* Spring Advice
  * Before : 메서드에 전달된 인수를 수정할 수 있고 예외를 발생시커 메서드 실행을 제어할 수 있음.
  * After : 메서드 호출이 결과를 반환한 이후 실행. 반환값 또한 수정 불가함.  
  * Around : Before + After 와 같지만, 반환값을 수정할 수 있음. 
  * Throw : 메서드 호출 조인포인트 이후 실행되나, 예외를 발생할 때에만 실행됨. 반환값 수정 불가하나 예외 타입을 변경할 수 있는 강력한 개념. 스프링은 afterThrowing() 메서드를 찾아 호출.
* Spring Advice 선택
  * 상황에 맞는 가장 구체적인 advice 선택. Before 만으로 가능한 경우, Around 사용하지 않도록. 


### Spring Advisor & Pointcut
* ProxyFactory 방식은 모든 메서드를 가리키는 Pointcut 으로 Advisor 를 구성.
* Advisor 를 적용할 메서드를 제한해야 할 때, Pointcut 을 이용

#### Pointcut interface
* Pointcut : ClassFilter 및 메서드Matcher 를 반환하는 함수를 재정의해 어드바이저 적용 대상을 지정. 
* ClassFilter : 검사할 Class 를 나타내는 Class 인스턴스를 전달. 
* 메서드Matcher
  * 일반적으로 정적 Pointcut 사용. 어드바이저가 오버헤드가 큰 경우, 동적 Pointcut 사용을 고려
  * 정적 Pointcut : 대상의 모든 메서드에 대해 matches()로 Pointcut 적용 대상여부를 검사 
  * 동적 Pointcut : 정적 검사 isRuntime() 이 true 일 때 추가로 matches() 수행. 특정 상황에서 포인트컷 적용을 제어할 수 있음.

#### Pointcut Advisor
* Spring 4.0 에서 여덟개의 Pointcut interface 구현체를 제공. 
* DefaultPointcutAdvisor : 하나의 Pointcut 을 하나의 Advisor 와 연결시키는 간단한 PointcutAdvisor.
* DynamicMethodMatcherPointcut : matches() 수행 결과에 따라 동적 검사 수행 여부를 결정. 정적 검사 수행결과는 성능을 위해 캐싱.
* StaticMethodMatcherPointcut : 메서드Matcher 구현체(추상클래스). matches()를 이용해 특정 메서드에만 advice를 적용.
  * NameMatchMethodPointcut : 메서드 이름을 기반으로 advice 적용여부 판단. 
  * JdkRegexpMethodPointcut : 정규식을 이용해 메서드 이름을 필터링하여 advice 적용여부를 판단. 
* AspectJExpressionPointcut : AspectJ 표현 언어를 사용해 Pointcut을 정의. 
  * aspectjweaver, aspectjrt 의존성 포함
  ```groovy
  dependencies {
    compile "org.aspectj:aspectjweaver:1.8.13"
    compile "org.aspectj:aspectjrt:1.8.13"
  }
  ```
  * 정의한 Expression 에 부합하는 메서드에 대해 advice를 적용. 
  ```java
  AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
  pc.setExpression("execution(* sing*(..))");
  ```
* AnnotationMatchingPointcut : 애너테이션을 포인트컷으로 사용. 
  * @Target 으로 애너테이션을 타입 레벨과 메서드 레벨로 정의할 수 있도록 정의. 
  * @Target 을 적용한 @interface 애너테이션 정의, advice 적용 대상 메서드에 애너테이션 선언. 
  ```java
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE, ElementType.메서드})
  public @interface AdviceRequired {
  }
  
  @AdviceRequired
  public void sing(Guitar guitar) {    
    System.out.println("play : " + guitar.play());
  }
  ```
  
#### Pointcut - Advanced
##### ControlFlowPointcut
* 특정 메서드 '에서' 호출될 때에만 어드바이스를 적용할 수 있음.
```java
// 특정 메서드가 ControlFlowDemo.test() 내부에서 실행될 때 어드바이스 적용 
Pointcut pc = new ControlFlowPointcut(ControlFlowDemo.class, "test");
```

##### ComposablePointcut
* 두 개 이상의 Pointcut 을 사용해야 하는 경우. 
  * ComposablePointcut.union() : 조인포인트 호출 체인에 'or' 조건을 추가
  * ComposablePointcut.intersection() : 조인포인트 호출 체인에 'and' 조건을 추가
  

#### Proxy
* Proxy 의 핵심 목적
  * 메서드 호출을 인터셉트, 필요한 경우 특정 메서드에 적용되는 advice 체인을 실행하는 것. 
* Proxy Type
  * JDK Proxy Class 기반 Proxy
    * interface Proxy 만을 생성할 수 있음. 
    * Proxy 를 적용하는 모든 객체는 적어도 하나의 interface 를 구현해야 함.
    * JVM 이 모든 메서드 호출을 인터셉트해 invoke() 호출, advice 적용 대상인 경우 advice 체인 호출 후 리플렉션을 이용해 메서드 수행.
    * 모든 메서드에 대해 invoke() 수행하므로 런타임 오버헤드를 발생시킴. 
  * CGLIB Enhancer 기반 Proxy 
    * CGLIB 가 각 Proxy 에 대해 동적으로 바이트코드를 생성, 재사용 가능할 경우 재사용. 
    * 호출된 메서드에 대해 invoke() 에서 수행하는 과정이 한 번씩만 수행됨. 
    * 고정 advice 체인을 적용한 CGLIB Proxy 의 성능은 JDK Proxy 와 비교해 우수함.


#### Spring Introduction
* 런타임에 기존 객체에 interface 구현체를 introduce 할 수 있다. 
* 기능이 횡단 관심사(crosscutting)이고 advice 를 사용해 쉽게 구현할 수 없을때 유용. 
* DeligatingIntroductionInterceptor 를 상속한 인터페이스 구현체를 정의
* 단, Introduction 과 Pointcut 을 병행할 수 없어 모든 메서드에 어드바이스가 적용됨으로 간주
* IsModifiedMixin 예제처럼, 객체 변경감지 등에 유용함. 


