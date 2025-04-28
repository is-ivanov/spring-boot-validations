package by.iivanov.springbootvalidation;

import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/with-validated")
class ControllerWithValidated {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ControllerWithValidated.class);

    @GetMapping("/{myId}")
    public void get(@RequestParam @Size(min = 5) String name,
                    @PathVariable @Min(15) Integer myId) {
        log.info("GET RequestParam name={}, PathVariable myId={}", name, myId);
    }

    @PostMapping("/valid")
    public void postVoidValid(@Valid @RequestBody MyRequest request) {
        log.info("POST Valid {}", request);
    }

    @PostMapping("/validated")
    public void postVoidValidated(@Validated @RequestBody MyRequest request) {
        log.info("POST Validated {}", request);
    }

}
