package dev.fabianalvarez.starwarsemployee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Schema(description = "Request object to create or update an Employee")
public record EmployeeRequest(

    @NotBlank(message = "First name cannot be empty")
    @Schema(description = "Employee's first name", example = "Luke")
    String firstName,

    @NotBlank(message = "Last name cannot be empty")
    @Schema(description = "Employee's last name", example = "Skywalker")
    String lastName,

    @NotBlank(message = "Role cannot be empty")
    @Schema(description = "Employee's role", example = "Jedi Knight")
    String role,

    @NotBlank(message = "Place cannot be empty")
    @Schema(description = "Employee's place of origin", example = "Tatooine")
    String place,

    @NotNull(message = "Birth date cannot be null")
    @PastOrPresent(message = "Birth date cannot be in the future")
    @Schema(description = "Employee's birth date", example = "1977-05-25")
    LocalDate birthDate,

    @Schema(description = "Is the employee active?", example = "true")
    boolean active
) {

}
