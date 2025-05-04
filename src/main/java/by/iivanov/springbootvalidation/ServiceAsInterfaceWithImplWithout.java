package by.iivanov.springbootvalidation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * В интерфейсе стоят валидационные аннотации, а в имплементации на параметрах метода нет.
 */
@RequiredArgsConstructor
@Service
@Validated
public class ServiceAsInterfaceWithImplWithout implements ServiceAsInterfaceWith {

    private static final Logger log = LoggerFactory.getLogger(ServiceAsInterfaceWithImplWithout.class);

    @Override
    public void parameters(String name,
                           Integer myId) {
        log.info("name={}, myId={}", name, myId);
    }

    @Override
    public void objectValid(MyRequest request) {
        log.info("Valid {}", request);
    }

    @Override
    public void objectValidated(MyRequest request) {
        log.info("Validated {}", request);
    }
}
