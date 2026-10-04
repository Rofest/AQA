package ru.negative;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.negative.dto.AuthRequestDTO;
import ru.negative.dto.AuthResponseDTO;
import ru.negative.dto.CreateBookingRequestDTO;
import ru.negative.dto.CreateBookingResponseDTO;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingNegativeTest {

    private static final String BOOKING_URL = "https://restful-booker.herokuapp.com";

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


    @Test
    void createBookingTest() {
        CreateBookingResponseDTO resp = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequestDTO())
                .post(BOOKING_URL + "/booking")
                .then()
                .extract()
                .as(CreateBookingResponseDTO.class);

        assertThat(resp.getBookingid()).isNotNull();
    }

    private static CreateBookingRequestDTO buildBookingRequestDTO() {
        return CreateBookingRequestDTO.builder()
                .firstname("Nikita")
                .lastname("Nik")
                .totalprice(1450)
                .depositpaid(true)
                .bookingdates(
                        CreateBookingRequestDTO.BookingDates.builder()
                                .checkin("2026-01-01")
                                .checkout("2027-01-01")
                                .build()
                )
                .additionalneeds("tea")
                .build();
    }


    static Stream<Arguments> invalidBookingData() {
        return Stream.of(
                Arguments.of(
                        CreateBookingRequestDTO.builder()
                                .lastname("Nik")
                                .totalprice(1450)
                                .depositpaid(true)
                                .bookingdates(
                                        CreateBookingRequestDTO.BookingDates.builder()
                                                .checkin("2026-01-01")
                                                .checkout("2027-01-01")
                                                .build()
                                )
                                .additionalneeds("tea")
                                .build(),
                        500,
                        "Без firstname"


                ),
                Arguments.of(

                        CreateBookingRequestDTO.builder()
                                .firstname("Nikita")
                                .totalprice(1450)
                                .depositpaid(true)
                                .bookingdates(
                                        CreateBookingRequestDTO.BookingDates.builder()
                                                .checkin("2026-01-01")
                                                .checkout("2027-01-01")
                                                .build()
                                )
                                .additionalneeds("tea")
                                .build(),
                        500,
                        "Без lastname"


                ),
                Arguments.of(

                        CreateBookingRequestDTO.builder()
                                .firstname("Nikita")
                                .lastname("Nik")
                                .totalprice(-500)
                                .depositpaid(true)
                                .bookingdates(
                                        CreateBookingRequestDTO.BookingDates.builder()
                                                .checkin("2026-01-01")
                                                .checkout("2027-01-01")
                                                .build()
                                )
                                .additionalneeds("tea")
                                .build(),
                        200,
                        "totalprice: -500"


                ),
                Arguments.of(

                        CreateBookingRequestDTO.builder()
                                .firstname("Nikita")
                                .lastname("Nik")
                                .totalprice(1234)
                                .depositpaid(true)
                                .bookingdates(
                                        CreateBookingRequestDTO.BookingDates.builder()
                                                .checkin("test")
                                                .checkout("2027-01-01")
                                                .build()
                                )
                                .additionalneeds("tea")
                                .build(),
                        200,
                        "checkin: не-дата"


                ),
                Arguments.of(

                        CreateBookingRequestDTO.builder()
                                .firstname("Nikita")
                                .lastname("Nik")
                                .totalprice(1234)
                                .depositpaid(true)
                                .bookingdates(
                                        CreateBookingRequestDTO.BookingDates.builder()
                                                .checkin("2028-01-01")
                                                .checkout("2027-01-01")
                                                .build()
                                )
                                .additionalneeds("tea")
                                .build(),
                        200,
                        "Дата выезда раньше даты заезда"


                ),
                Arguments.of(

                        CreateBookingRequestDTO.builder()
                                .build(),
                        500,
                        "Пустое body"
                )

        );
    }

    @ParameterizedTest(name = "{index}:{2}")
    @MethodSource("invalidBookingData")
    void createBookingNegativeTest(
            CreateBookingRequestDTO request,
            int expectedStatusCode,
            String caseName
    ) {

        Response resp = given()
                .contentType(ContentType.JSON)
                .body(request)
                .post(BOOKING_URL + "/booking")
                .then()
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(expectedStatusCode);
    }

}
