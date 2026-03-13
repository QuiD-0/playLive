# playLive 개선 TODO

## 보안
- [x] 1. Vue Router 라우트 가드 추가
  - 비로그인 사용자가 /studio 등 인증 필요 페이지에 접근하지 못하도록 beforeEach 네비게이션 가드 추가
  - 로그인 안 된 상태면 /login으로 리다이렉트
- [x] 2. 스트림 키 마스킹 처리
  - StageManage.vue에서 스트림 키가 평문으로 노출되는 문제
  - 기본 마스킹(*****) 처리하고 '보기/복사' 버튼으로 변경

## 성능
- [ ] 3. 라우트 지연 로딩 (코드 스플리팅)
  - router.js에서 정적 import를 동적 import로 전환
  - 초기 번들 크기 감소
- [ ] 4. 채팅 메시지 가상 스크롤 적용
  - Chatting.vue에서 채팅 메시지가 무한정 쌓이는 문제
  - 메시지 상한(예: 200개) 설정하거나 가상 스크롤 라이브러리 적용

## 코드 품질
- [ ] 5. DOM 직접 조작 제거 → Vue ref 사용
  - ProfileManage.vue 등에서 document.querySelector(), document.createElement() 사용하는 부분을 Vue template ref로 리팩토링
- [ ] 9. Vuex → Pinia 전환
  - Vue 3 공식 상태 관리인 Pinia로 전환
  - userStore, clientStore를 Pinia store로 마이그레이션

## UX
- [ ] 6. API 호출 시 로딩 상태 추가
  - 각 페이지에서 API 호출 시 로딩 스피너 또는 스켈레톤 UI 추가
  - 현재 Live.vue 외에는 로딩 상태가 없음
- [ ] 7. 글로벌 에러 처리 추가
  - Vue app.config.errorHandler 설정 + axios 글로벌 에러 인터셉터 개선
  - 사용자 친화적 에러 토스트/페이지 표시
- [ ] 11. 반응형 디자인 (모바일 지원)
  - 모바일 환경에서도 사용 가능하도록 반응형 CSS 적용

## 개발 인프라
- [ ] 8. ESLint + Prettier 설정
  - ESLint 설정 파일 생성 + Prettier 연동
  - Vue 3 + Composition API에 맞는 규칙 적용
- [x] 12. Vitest 테스트 환경 구축
  - Vitest + Vue Test Utils 설치 및 설정
  - 주요 컴포넌트/유틸리티 단위 테스트 작성

## 기능 확장
- [ ] 10. 채팅 기능 고도화
  - 타임스탬프 표시, 사용자 구분(방송자 뱃지)
  - 채팅 관리 기능(밴/뮤트) 추가

---

# 백엔드 개선 TODO

## CRITICAL (즉시 수정 필요)
- [x] ~~B1. JWT 시크릿 키 환경변수 분리~~ (스킵 - yaml이 git에 안 올라감)
- [x] ~~B2. AWS 자격증명 제거~~ (스킵 - yaml이 git에 안 올라감)
- [x] B3. WebSocket 인증 추가
  - HandshakeInterceptor로 JWT 검증, 실패 시 연결 거부
  - 서버에서 세션 기반 사용자 정보 사용 (사칭 방지)
  - 프론트 비로그인 시 채팅 읽기 전용 + 로그인 안내 UI

## HIGH
- [ ] B4. CORS 설정 강화
  - 와일드카드(*) origin 제거, 허용 도메인 명시
- [ ] B5. N+1 쿼리 수정
  - MemberAuthorityRepository.findByUserId()가 전체 테이블을 로드하는 문제
  - memberId 기반 쿼리로 변경
- [ ] B6. 채팅 세션 Redis 전환
  - 인메모리 ChatSessionRepository를 Redis 기반으로 전환
  - 서버 재시작 시 세션 유실 방지
- [ ] B7. 백엔드 테스트 커버리지 확대
  - Stage, Chat, Token, Security 모듈 테스트 작성 (현재 0개)

## MEDIUM
- [ ] B8. JWT 라이브러리 업데이트
  - JJWT 0.11.2 → 0.12.x 업데이트
- [ ] B9. 채팅 메시지 XSS 방지
  - Chat.toJson()에서 JSON 문자열 이스케이프 처리
- [ ] B10. 에러 핸들링 개선
  - 스택트레이스 노출 제거, 도메인별 커스텀 예외 추가
- [ ] B11. API 문서화 (Swagger/OpenAPI)
  - Springdoc-OpenAPI 적용
- [ ] B12. 요청 유효성 검증 추가
  - @Valid 어노테이션 적용, 문자열 길이 제한 등
