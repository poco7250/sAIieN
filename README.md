# sAIieN : AI 자동화 Sales CRM
![AI 12조 1P 설명서](https://github.com/user-attachments/assets/f9fd4290-24f0-4ff5-a64b-40a4ff96256d)
## ❓ 기획 의도
기존의 CRM 서비스는 사용하기 너무 어려우며, 이용하는데 시간이 너무 많이 걸리는 문제도 존재합니다.<br>
sAlieN은 사용자들에게 더 편리한 CRM 서비스를 제공하면서 백오피스 자동화를 통한 시간 및 비용 절감과 B2B sales 특화, 실무 sales flow를 반영하여 비즈니스 문제를 효과적으로 해결하는데 도움을 줍니다.
## 📌 프로젝트 소개
리드, 영업기회 확인 및 진행, 주문 등 CRM의 기본적인 기능들에 더하여
- AI 자동 리드 생성
- AI 제안서 생성
- AI 어시스턴트

기능을 추가하여 더 편리하고 효과적인 CRM 서비스를 제공합니다.
## ⌛ 제작 기간
2024년 12월 30일 ~ 2025년 2월 7일 (6주)
## ⚒️ 개발 환경 및 사용 기술
[기술]
<br><br>
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/json-000000?style=for-the-badge&logo=json&logoColor=white">
<br><br>
[개발 환경]
<br><br>
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<img src="https://img.shields.io/badge/postgresql-4169E1?style=for-the-badge&logo=postgresql&logoColor=white">
<img src="https://img.shields.io/badge/dbeaver-382923?style=for-the-badge&logo=dbeaver&logoColor=white">
<br><br>
[협업]
<br><br>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white">
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
## ⛓️ ERD
![sAIien-ERD](https://github.com/user-attachments/assets/3190df8a-f7ba-4f54-a4a4-0b92cf9cf660)
## 시스템 아키텍처
![sAIien-시스템 아키텍쳐](https://github.com/user-attachments/assets/28c68857-baac-47db-b794-63fd6188dbfc)
<br>
Spring Boot, PostgreSQL, AI 모델 서버를 각각 Docker 컨테이너로 실행<br>
Spring Boot는 JPA를 이용해 PostgreSQL과 연결되며,
AI모델 서버(Fast API)와는 REST API를 통해 통신<br>
<br>
Docker-compose를 이용해 컨테이너간 네트워크를 구성하여 Spring Boot가 DB와 AI 서버를 효율적으로 호출할 수 있도록 함

## 서비스 플로우
![sAIien-서비스 플로우](https://github.com/user-attachments/assets/c7894cdc-11a7-461d-8636-af23faf53270)
<br>
기본적인 CRM의 리드 – 영업 – 계약 – 주문 및 관리 흐름에서

- AI 통화 요약 자동화를 통한 영업 기회 분석 시간 단축<br>
- AI 어시스턴트를 통한 업무 자동화<br>
- 영업 기회 기반 AI 제안서 자동 생성<br>

기능을 통해 업무 효율을 극대화하여 영업 성공률을 높임
<br>

## 맡은 역할
- 프로젝트 인원 : 7명(AI 모델 3명, 백엔드 및 프론트엔드 4명)
- 백엔드(40%), 프론트엔드(25%) 담당

## ⚙️ 기능 구현
- 일반 사원 전용 메인화면 구성
- CRM 서비스에 맞는 카드, 그래프, 테이블 데이터를 Repository에서 조회한 후, Service에서 비즈니스 로직을 적용하고,<br>Controller에서 Model에 담아 View로 전달하는 과정을 구현 및 백엔드 API 호출을 통해 그래프 표시
- 상세페이지 로딩 시, 테이블의 Primary Key인 ID를 기준으로 인덱스를 활용하여 데이터를 조회, B+트리 인덱스에 의한 검색을 O(log N) 시간 복잡도로 처리하여 데이터 양이 증가해도 로딩 속도가 빠르게 유지되도록 구현
- 상세페이지 내용 구성 및 Status 및 progress bar, 뒤로가기 버튼 표시
- 테이블을 SSR(Server-side Rendering)으로 구현 및 외래키 조회시 열의 수를 제한하여 페이지 로딩 속도를 개선
- Code Metrics 및 SonarQube사용으로 코드 보완 및 입력 값에 대한 유효성 검사 추가
