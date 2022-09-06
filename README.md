# study-spring5

## 참고문헌
* 전문가를 위한 Spring5 개정판, 길벗출판사

## Environments
* Framework : Spring 5.0
* SDK : Azul zulu version 13.0.12
* Gradle : 6.2


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
* BeanFactory 를 상속한 인터페이스. 
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


## Spring Structure
### Bean Lifecycle Management
* IoC 주요 기능으로, 생성 또는 소멸과 같은 Lifecycle 특정 시점에 통지받을 수 있게 Bean을 생성.
* post-initialization
  * 초기화 이후 이벤트. Bean에 모든 프로퍼티 값을 설정하고 의존성 점검을 마친 직후 발생. 
* pre-destruction
  * 소멸 전 이벤트. 스프링이 Bean 인스턴스를 소멸시키기 직전에 발생. 
  * Prototype Bean의 경우 소멸 전 이벤트를 통지하지 않음.
* Spring 이벤트 수신 메커니즘
  * 인터페이스 기반 : 수신을 원하는 통지 유형 인터페이스를 Bean 에 구현 시 콜백을 통한 통지. (InitializingBean, DisposableBean)
  * 메서드 기반 : 초기화/소멸 시 호출할 메서드명을 ApplicationContext 구성에 지정. (init-method, destroy-method)
  * 애너테이션 기반 : 초기화/소멸 시 호출할 메서드에 애너테이션 지정. (JSR-250 표준)
* 표준에 따라 라이프사이클 관리를 직접 구현하는 경우, 이식성을 높일 수 있음. 

### Spring Aware
* Bean 이 ApplicationContext 컨테이너에 접근하게 하는 특정 상황.
* ex) 런타임에 로그를 기록할 때, Bean Name을 알아낼 수 있으면 유용함. 
* 단, Bean Name 에 접근할 수 있다고 해서 Bean 이름에 비즈니스적인 의미를 부여해서는 안 됨.
  * 필요 시 Nameable 구현체 등으로 의존성 주입해 사용. 

### Factory Bean
* new 연산자로는 생성할 수 없는 객체를 관리하기 위한 어댑터로 FactoryBean 인터페이스를 제공.
* FactoryBean.getObject() 를 통해 인스턴스 획득

### Java Bean PropertyEditor
* property 값을 원래 자료 타입에서 String 으로 변환하거나, 반대의 작용을 하는 인터페이스.

### MessageSource 
* 스프링의 i18n 국제화 지원 기능으로 MessageSource 인터페이스를 사용. 
  * ResourceBundleMessageSource : ResourceBundle 을 이용하여 메시지 로드
  * ReloadableResourceBundleMessageSource : 메시지 소스 파일에서 주기적으로 다시 읽어들일 수 있음. 
  * StaticMessageSource : 상용 애플리케이션 사용 금지. 

### Spring Profile
* 특정 프로파일이 활성화되면 해당 프로파일에 정의된 ApplicationContext 인스턴스만 구성됨.
* 실행 환경에 따라 사용할 프로파일을 다르게 적용할 수 있음.
  * Environment 인터페이스를 이용한 구성 
  * JSR-330 애너테이션을 이용한 구성