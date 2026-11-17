# test-repo — Claude Code Action 실습

간단한 주문(Order) API에 AI 자동화를 붙여 보는 실습용 Spring Boot 프로젝트입니다.

| 파일 | 역할 |
| --- | --- |
| `.github/workflows/ci.yml` | 빌드·테스트 (`CI`) |
| `.github/workflows/claude-review.yml` | PR 자동 리뷰 |
| `.github/workflows/claude.yml` | `@claude` 멘션 대응 |
| `.github/workflows/claude-ci-failure.yml` | CI 실패 원인 분석 |
| `.github/workflows/claude-release-notes.yml` | `v*` 태그 푸시 시 릴리즈 노트 Draft 생성 |
| `.coderabbit.yaml` | CodeRabbit 리뷰 설정 |
| `CLAUDE.md` | 프로젝트 규칙 |

## 로컬 실행

```bash
./gradlew test
./gradlew bootRun
```
