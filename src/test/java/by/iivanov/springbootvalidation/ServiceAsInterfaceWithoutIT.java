package by.iivanov.springbootvalidation;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintDeclarationException;

/**
 * Тесты сервиса с аннотацией {@link org.springframework.validation.annotation.Validated}
 * когда есть интерфейс и в нём на методах нет валидационных аннотаций.
 */
class ServiceAsInterfaceWithoutIT extends BaseIT {

    @Autowired
    private ServiceAsInterfaceWithout sut;

    /**
     * Выбрасывается ошибка {@link ConstraintDeclarationException} при вызове валидации параметров метода.
     * Т.к. в методе в интерфейсе не проставлены валидационные аннотации (по сути нарушается LSP).
     */
    @Test
    void parameters() {
        Throwable thrown = BDDAssertions.catchThrowable(() -> sut.parameters("Four", 10));
        // THEN:
        BDDAssertions.then(thrown).isInstanceOf(ConstraintDeclarationException.class)
                .hasMessageContaining("HV000151: A method overriding another method must "
                        + "not redefine the parameter constraint configuration");
    }

    /**
     * Выбрасывается ошибка {@link ConstraintDeclarationException} при вызове валидации параметров метода.
     * Т.к. в методе в интерфейсе не проставлены валидационные аннотации (по сути нарушается LSP).
     */
    @Test
    void objectValid() {
        MyRequest invalidRequest = new MyRequest("less 15", null);
        Throwable thrown = BDDAssertions.catchThrowable(() -> sut.objectValid(invalidRequest));
        // THEN:
        BDDAssertions.then(thrown).isInstanceOf(ConstraintDeclarationException.class)
                .hasMessageContaining("HV000151: A method overriding another method must "
                        + "not redefine the parameter constraint configuration");
    }

    /**
     * Выбрасывается ошибка {@link ConstraintDeclarationException} при вызове валидации параметров метода.
     * Т.к. в методе в интерфейсе не проставлены валидационные аннотации (по сути нарушается LSP).
     */
    @Test
    void objectValidated() {
        MyRequest invalidRequest = new MyRequest("less 15", null);
        Throwable thrown = BDDAssertions.catchThrowable(() -> sut.objectValidated(invalidRequest));
        // THEN:
        BDDAssertions.then(thrown).isInstanceOf(ConstraintDeclarationException.class)
                .hasMessageContaining("HV000151: A method overriding another method must "
                        + "not redefine the parameter constraint configuration");
    }

}
