# CLAUDE.md

## 프로젝트 개요
- Spring Boot 4 / Java 21 / Gradle / MySQL (로컬·테스트는 H2 인메모리)
- 레이어 구조: controller → service → repository
- 패키지: `com.example.order` (domain, dto, exception, controller, service, repository)

## 코딩 컨벤션
- DTO는 record로 작성한다.
- Controller에는 비즈니스 로직을 두지 않는다.
- 조회 메서드에는 @Transactional(readOnly = true)를 붙인다.
- 예외는 BusinessException을 상속한 커스텀 예외를 사용하고, GlobalExceptionHandler에서 처리한다.

## 테스트
- 테스트 실행: ./gradlew test
- 서비스 로직은 단위 테스트(Mockito), API는 @WebMvcTest로 테스트한다.
- Spring Boot 4 기준: @MockBean 대신 @MockitoBean, @WebMvcTest는 `org.springframework.boot.webmvc.test.autoconfigure` 패키지.

## 리뷰 시 중점 사항
- 트랜잭션 경계, N+1 쿼리, 예외 처리 누락, 입력값 검증 누락
- 스타일·포매팅은 Checkstyle이 검사하므로 지적하지 않는다.
