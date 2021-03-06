# Spring Boot TDD Start! - Kotlin

[유스콘'21](https://frost-witch-afb.notion.site/YOUTHCON-21-365e94c3df3443e5b1322520a8b1a2ef) Spring Boot TDD Start 세션  
2021년 12월 19일 늦은 4시에 유스콘에서 만나요 🙌

## 무엇을 하나요?
🚀 스프링 부트와 TDD로  간단한 웹 어플리케이션을 만듭니다.
- 웹 어플리케이션에서 TDD 컨셉 이해하기
- 테스트를 진행하면서 만날 수 있는 문제들 해결하기

## 누구를 대상으로 하나요?
👶 스프링으로 개발하는 0~3년차 개발자 혹은 TDD에 대해서 어디선가 들어본 당신!

## TDD가 뭐죠? 🤔

![image](https://user-images.githubusercontent.com/34270397/145977256-c2499c5f-ca8d-4fb9-b64e-6d07cfd9824e.png)

TDD는 작은 단위에 테스트 케이스를 작성하고 이를 통과하는 코드를 작성하는 단계를 반복하여 전체적인 소프트웨어를 만드는 방법론입니다.  
테스트를 미리 작성하면서 프로그래밍 목적과 구조를 톺아볼 수 있어서 좋은 코드를 작성하도록 도와줘요. 🥰


## 이번에 다루지 않는 것들에 대하여..
😥 자바 / 스프링 / 기타 프레임워크 및 라이브러리에 대해 자세히 다루지 않아요.

## 무엇을 만드나요?
인수테스트부터 작성하는 하향식 방법을 통해 조회 API부터 외부서비스 연동까지 신입이 실무에서 만날 수 있는 문제를 함께 풀어요.

**후기 조회 하기 API**
- [x] 후기의 ID를 요청 값으로 받습니다.
- [x] 요청 ID에 대한 후기를 찾아 응답값으로 내려줍니다. (200 OK)
- [x] 응답에는 반드시 본문을 포함해야 합니다.
- [x] 응답에는 반드시 연락처를 포함해야 합니다.
- [x] 후기가 존재하지 않는다면 에러를 응답해야 합니다. (404 Not Found)

**후기 작성자에게 선물하기 API**
- [x] 후기의 ID를 요청 값으로 받습니다.
- [x] 선물은 후기당 한번만 요청 할 수 있습니다.
- [x] 선물하기에 성공하면 후기의 현재 상태를 응답합니다. (200 OK)
- [x] 선물하기는 아래의 API를 호출하여 수행합니다.

#### 🎁 선물하기 API
 ```
요청
 curl --request POST \
  --url youthcon.seok2.dev/apis/v1/send
  --header 'Content-Type: application/json' \
  --data '{"number":"01012345678","amount":10000}'
 ```


 ```
 응답
{
  "id" : "20211219YOUTHCONISGOOD"
  "amount" : 10000
}
```

#### ATDD(인수테스트)는 또 무엇인가요? :scream:
개발 이전에 사용자, 테스터 및 개발자가 인수조건(Acceptance Creteria)을 정의하는 협업 실천법이에요.  
모든 프로젝트 구성원이 **수행해야 할 작업과 요구사항을 정확히 이해할수 있도록** 도와줘요.


## 기술 스택은 아래와 같습니다 📝
- Java 11
- Kotlin 1.6
- Gradle 7.3
- Spring Boot 2.6
- Spring Data Jpa
- H2 Database
- REST-assured
- Mockk
- Springmockk
