package by.iivanov.springbootvalidation;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;

public record MyRequest(@Size(min = 15) String value,
                        @NotNull LocalDate birthdate) {
}
