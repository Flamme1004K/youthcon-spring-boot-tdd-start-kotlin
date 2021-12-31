package com.youthcon.tdd.review

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

/*
**후기 조회 하기 API**
- [ ] 후기의 ID를 요청 값으로 받습니다.
- [ ] 요청 ID에 대한 후기를 찾아 응답값으로 내려줍니다. (200 OK)
- [ ] 응답에는 반드시 본문을 포함해야 합니다.
- [ ] 응답에는 반드시 연락처를 포함해야 합니다.
- [ ] 후기가 존재하지 않는다면 에러를 응답해야 합니다. (404 Not Found)

**후기 작성자에게 선물하기 API**
- [ ] 후기의 ID를 요청 값으로 받습니다.
- [ ] 선물은 후기당 한번만 요청 할 수 있습니다.
- [ ] 선물하기에 성공하면 후기의 현재 상태를 응답합니다. (200 OK)
- [ ] 선물하기는 아래의 API를 호출하여 수행합니다.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest {

    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun `후기 조회 성공`() {
        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .`when`()
            .get("/reviews/1")
            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(1))
            .body("content", equalTo("재밌어요"))
            .body("phoneNumber", equalTo("010-1111-2222"))
    }

    @Test
    fun `후기 조회 실패`() {
        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .`when`()
            .get("/reviews/1000")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
    }

    @Test
    fun `선물하기 성공`() {
        given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .`when`()
            .put("/reviews/2")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("sent", equalTo(true))
    }
}