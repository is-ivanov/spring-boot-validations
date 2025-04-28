package by.iivanov.springbootvalidation;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Тесты сервиса с аннотацией {@link org.springframework.validation.annotation.Validated}.
 */
class ServiceAsClassWithIT extends BaseIT {

    @Autowired
    private ServiceAsClassWith sut;

    /**
     * С '@Validated' на сервисе происходит валидация параметров метода и выбрасывается
     * {@link ConstraintViolationException}.
     */
    @Test
    void parameters() {
        Throwable thrown = BDDAssertions.catchThrowable(() -> sut.parameters("Four", 10));
        // THEN:
        BDDAssertions.then(thrown).isInstanceOf(ConstraintViolationException.class)
                .hasMessageContainingAll("parameters.myId: должно быть не меньше 15",
                        "parameters.name: размер должен находиться в диапазоне от 5 до 2147483647");
    }

    /**
     * С '@Validated' на сервисе происходит валидация объекта с '@Valid' и выбрасывается
     * {@link ConstraintViolationException}.
     */
    @Test
    void objectValid() {
        MyRequest invalidRequest = new MyRequest("less 15", null);
        Throwable thrown = BDDAssertions.catchThrowable(() -> sut.objectValid(invalidRequest));
        // THEN:
        BDDAssertions.then(thrown).isInstanceOf(ConstraintViolationException.class)
                .hasMessageContainingAll("objectValid.request.birthdate: не должно равняться null",
                        "objectValid.request.value: размер должен находиться в диапазоне от 15 до 2147483647");
    }

    /**
     * С '@Validated' на сервисе не происходит валидация объекта с '@Validated'.
     */
    @Test
    void objectValidated() {
        MyRequest invalidRequest = new MyRequest("less 15", null);
        BDDAssertions.thenCode(() -> sut.objectValidated(invalidRequest)).doesNotThrowAnyException();
    }

}
