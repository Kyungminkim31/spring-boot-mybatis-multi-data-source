# spring-boot-mybatis-multi-data-source
본 예제는 Spring Boot 와 Mybatis 를 이용한 Multi DataSource 연결법을 공유하기 위한 예제입니다.

## 설정 방법
설정 방법은 아래와 같은 순서로 설정합니다.
1. Data 관련 의존성 등록
    - `spring-boot-starter-jdbc` 또는 `spring-boot-starter-data-jpa`
      > *`spring-boot-starget-data-jpa` 내 `spring-boot-starter-jdbc`가 포함되어 있습니다.*
      > <br/>JPA 없이 MyBatis단독으로 사용할 경우 `spring-boot-starter-jdbc`만 등록하여도 정상적으로 동작합니다.
      > <br/>본 예제는 'spring-boot-start-jdbc'로 작성되었습니다. *
    - Mybatis를 위한 `mybatis-spring-boot-autoconfigure` 
2. DataSource 설정을 위한 Java Configuration Class 작성
    - 메인으로 사용할 데이터소스에 `@Primary` 어노테이션이 필수로 붙어야 합니다.
    - `@Bean` 어노테이션 등록 시 `name` 속성값을 이용하여 이름을 지정합니다. 향후 Bean 사용시 해당 값을 통해 구분지어 사용할수 있습니다.
    - @MapperScan 를 통해 Mapper 클래스를 Bean으로 등록되게 합니다.
      > SqlSessionTemplateRef 속성값을 통해 데이터소스별 올바른 Mapper 클래스를 bound 할수 있습니다.
      > 만약 이부분 설정이 잘못되었다면 Invalid Bound Exception (Not Found)로 예외처리가 됩니다.
3. Properties 설정를 통한 데이터소스 속성값 제어
    - `connectionTimeout` 등 다양한 속성값들을 properties를 통해 제어 할수 있습니다.
4. 등록된 DataSource를 이용하여 SqlSessionFactory Bean을 등록
    - 2번과 마찬가지로 `@Bean` 등록시 `name` 속성값을 지정해줍니다. 
5. 빌드 된 SqlSessionFactory 인스턴스를 이용하여 SqlSessionTemplate 등록
    - 2번과 마찬가지로 '@Bean' 등록시 'name' 속성값을 지정하여 줍니다.
    - 지정한 `name` 속성값을 3번의 `@MapperScan` 에서 `SqlSessionTemplateRef` 속성으로 지정하여 자동 Mapper 클래스 스캔시 데이터 소스별 올바른 매퍼 클래스들이 빈으로 등록 될수 있게 해줍니다.

## 참고사항
본 예제 의 두번째 데이터 소스의 경우 Lazy Initializing 이 되어서 Spring Boot 실행시 데이터 소스 로딩이 되지 않습니다.
이후 서버 상에서 두번째 데이터 소스 관련 Sql 이 실행될 때 해당 데이터 소스가 초기화 되어 등록 됩니다.

