package by.iivanov.springbootvalidation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Service
@Validated
public class ServiceAsClassWith {

    private static final Logger log = LoggerFactory.getLogger(ServiceAsClassWith.class);

    public void parameters(@Size(min = 5) String name,
                           @Min(15) Integer myId) {
        log.info("name={}, myId={}", name, myId);
    }

    public void objectValid(@Valid MyRequest request) {
        log.info("Valid {}", request);
    }

    public void objectValidated(@Validated MyRequest request) {
        log.info("Validated {}", request);
    }
}
