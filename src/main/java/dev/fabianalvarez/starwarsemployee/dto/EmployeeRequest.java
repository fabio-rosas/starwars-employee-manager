package dev.fabianalvarez.starwarsemployee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Request object to create or update an Employee")
public record EmployeeRequest(

    @Schema(description = "Employee's first name", example = "Luke")
    String firstName,

    @Schema(description = "Employee's last name", example = "Skywalker")
    String lastName,

    @Schema(description = "Employee's role", example = "Jedi Knight")
    String role,

    @Schema(description = "Employee's place of origin", example = "Tatooine")
    String place,

    @Schema(description = "Employee's birth date", example = "1977-05-25")
    LocalDate birthDate,

    @Schema(description = "Is the employee active?", example = "true")
    boolean active
) {

}
