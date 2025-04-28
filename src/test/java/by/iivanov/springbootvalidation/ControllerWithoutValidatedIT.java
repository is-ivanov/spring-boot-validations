package by.iivanov.springbootvalidation;

import io.restassured.RestAssured;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

/**
 * Тесты контроллера без аннотации {@link org.springframework.validation.annotation.Validated}.
 */
class ControllerWithoutValidatedIT extends BaseIT {

    private static final String PATH = "/without-validated";

    /**
     * Без '@Validated' на контроллере не происходит валидация параметров запросов
     * и даже с невалидными значениями получаем 200.
     */
    @Test
    void get() {
        RestAssured
                .given()
                    .spec(RestAssuredHelper.getDefaultRequestSpec())
                    .pathParam("myId", 10)
                    .queryParam("name", "Four")
                .when()
                    .get(PATH + "/{myId}")
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

    /**
     * Без '@Validated' на контроллере происходит валидация параметров запросов
     * и с невалидными значениями получаем 400.
     * Выбрасывается {@link MethodArgumentNotValidException}
     */
    @Test
    void postVoidValid(CapturedOutput output) {
        String invalidRequest = """
                {
                    "value": "less 15",
                    "birthdate": null
                }""";

        RestAssured
                .given()
                    .spec(RestAssuredHelper.getDefaultRequestSpec())
                    .body(invalidRequest)
                .when()
                    .post(PATH + "/valid")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", allOf(
                        containsString("Field error in object 'myRequest' on field 'birthdate': rejected value [null]"),
                        containsString("Field error in object 'myRequest' on field 'value': rejected value [less 15]"))
                );
        BDDAssertions.then(output.getOut())
                .contains("Request URL /without-validated/valid. MethodArgumentNotValidException");
    }

    /**
     * Без '@Validated' на контроллере происходит валидация параметров запросов
     * и с невалидными значениями получаем 400.
     * Выбрасывается {@link MethodArgumentNotValidException}
     */
    @Test
    void postVoidValidated(CapturedOutput output) {
        String invalidRequest = """
                {
                    "value": "less 15",
                    "birthdate": null
                }""";

        RestAssured
                .given()
                    .spec(RestAssuredHelper.getDefaultRequestSpec())
                    .body(invalidRequest)
                .when()
                    .post(PATH + "/validated")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", allOf(
                        containsString("Field error in object 'myRequest' on field 'birthdate': rejected value [null]"),
                        containsString("Field error in object 'myRequest' on field 'value': rejected value [less 15]"))
                );
        BDDAssertions.then(output.getOut())
                .contains("Request URL /without-validated/validated. MethodArgumentNotValidException");
    }
}
