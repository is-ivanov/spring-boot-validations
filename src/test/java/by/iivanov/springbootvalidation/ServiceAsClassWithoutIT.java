package by.iivanov.springbootvalidation;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * Тесты сервиса без аннотации {@link org.springframework.validation.annotation.Validated}.
 */
class ServiceAsClassWithoutIT extends BaseIT {

    @Autowired
    private ServiceAsClassWithout sut;

    /**
     * Без '@Validated' на сервисе не происходит валидация параметров метода.
     */
    @Test
    void parameters() {
        BDDAssertions.thenCode(() -> sut.parameters("Four", 10)).doesNotThrowAnyException();
    }

    /**
     * Без '@Validated' на сервисе не происходит валидация объекта с '@Valid'.
     */
    @Test
    void objectValid() {
        MyRequest invalidRequest = new MyRequest("less 15", null);
        BDDAssertions.thenCode(() -> sut.objectValid(invalidRequest)).doesNotThrowAnyException();
    }

    /**
     * Без '@Validated' на сервисе не происходит валидация объекта с '@Validated'.
     */
    @Test
    void objectValidated() {
        MyRequest invalidRequest = new MyRequest("less 15", null);
        BDDAssertions.thenCode(() -> sut.objectValidated(invalidRequest)).doesNotThrowAnyException();
    }

}
