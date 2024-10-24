# 여보게 추천 기능 리팩토링

<table>
<tr>
<td>
기존의 여보게 추천 기능 관련 구현 방식 및 설계에 대해 필요한 개선점이 있어, 이를 리팩토링하기 위한 프로젝트
</td>
</tr>
</table>

> 우선은 추천 기능 관련한 부분만 리팩토링 진행

---

### 기존 여보게 추천 로직의 문제점 인식

**개인 추천**

- Service 로직 세부 계층화
  - `RecommenderService`, `GroupRecommenderService`의 메서드에서 구현부와 비즈니스 로직이 섞여 있음
- `-Recommender` 의존성 문제
  - 각 클래스 별로 책임이 명확하게 분리 되었다고 어려움. 그 결과 중복되는 코드가 있고 라이브러리(WebClient)에 강하게 의존
- WebClient 의존성
  - 비동기 논블로킹으로 외부 API 호출은 좋았으나, 단순히 개인 추천 기능 하나만을 위해 Spring WebFlux라는 다소 무거운 라이브러리 의존 필요


**그룹 추천**

- 그룹 매칭 시 스레드 강제 블로킹
  - 다른 사용자의 요청을 기다리기 위해 서버에서 강제로 요청 처리 스레드를 블로킹
- 그룹 매칭에 필요한 사용자 요청을 서버 내부에서 관리
  - `GroupRecommenderService` 내부에서 `HashMap`으로 관리
