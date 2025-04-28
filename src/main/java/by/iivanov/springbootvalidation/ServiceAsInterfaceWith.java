package by.iivanov.springbootvalidation;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public interface ServiceAsInterfaceWith {
    void parameters(@Size(min = 5) String name,
                    @Min(15) Integer myId);

    void objectValid(@Valid MyRequest request);

    void objectValidated(@Validated MyRequest request);
}
