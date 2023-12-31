package com.sotelo.martin.restspringboot.bookmarks;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesRegex;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
public class BookmarkControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.4-alpine"));

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Sql("/test_data.sql")
    void shouldGetBookmarksByPage() {
        given().contentType(ContentType.JSON)
               .when()
               .get("/api/bookmarks?page=1&size=10")
               .then()
               .statusCode(200)
               .body("data.size()", equalTo(10))
               .body("totalElements", equalTo(15))
               .body("pageNumber", equalTo(1))
               .body("totalPages", equalTo(2))
               .body("isFirst", equalTo(true))
               .body("isLast", equalTo(false))
               .body("hasNext", equalTo(true))
               .body("hasPrevious", equalTo(false));
    }

    @Test
    void shouldCreateBookmarkSuccessfully() {
        given().contentType(ContentType.JSON)
               .body(
                   """
                     {
                         "title": "SivaLabs blog",
                         "url": "https://sivalabs.in"
                     }
                   """)
               .when()
               .post("/api/bookmarks")
               .then()
               .statusCode(201)
               .header("Location", matchesRegex(".*/api/bookmarks/[0-9]+$"))
               .body("id", notNullValue())
               .body("title", equalTo("SivaLabs blog"))
               .body("url", equalTo("https://sivalabs.in"))
               .body("createdAt", notNullValue())
               .body("updatedAt", nullValue());
    }
}
