package by.iivanov.springbootvalidation;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class RestAssuredHelper {

    /**
     * Возвращает спецификацию запроса по умолчанию.
     *
     * @return спецификация запроса {@code RestAssured} по умолчанию.
     */
    public static RequestSpecification getDefaultRequestSpec() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Возвращает спецификацию успешного ответа типа {@code JSON}.
     *
     * @return спецификация {@code RestAssured} для проверки успешного ответа.
     */
    public static ResponseSpecification getOkJsonResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.OK.value())
                .expectContentType(MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
