package ru.negative;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.negative.dto.AuthRequestDTO;
import ru.negative.dto.AuthResponseDTO;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;

public class AuthNegativeTest {

    private static final String BOOKING_URL = "https://restful-booker.herokuapp.com";



    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


    @Test
    void authTest() {
        String user = "admin";
        String password = "password123";

        Response resp = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequestDTO(user, password))
                .post(BOOKING_URL + "/auth")
                .then()
                .extract().response();

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.as(AuthResponseDTO.class).getToken()).isNotNull();

    }

    @Test
    void authTestInvalidPass() {
        String user = "admin";
        String password = "invalid";

        Response resp = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequestDTO(user, password))
                .post(BOOKING_URL + "/auth")
                .then()
                .extract().response();

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.as(AuthResponseDTO.class).getToken()).isNull();
    }

    @Test
    void authTestInvalidLogin(){
        String user = "invalid";
        String password = "password123";

        Response resp = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequestDTO(user,password))
                .post(BOOKING_URL + "/auth")
                .then()
                .extract().response();

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.as(AuthResponseDTO.class).getToken()).isNull();
    }


    static Stream<Arguments> invalidAuth(){
        return Stream.of(
                Arguments.of("admin", ""),
                Arguments.of("","password123")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidAuth")
    void authInvalid(String user, String password){
        Response resp = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequestDTO(user,password))
                .post(BOOKING_URL + "/auth")
                .then()
                .extract().response();

        assertThat(resp.statusCode()).isEqualTo(200);
        assertThat(resp.as(AuthResponseDTO.class).getToken()).isNull();


    }


}







