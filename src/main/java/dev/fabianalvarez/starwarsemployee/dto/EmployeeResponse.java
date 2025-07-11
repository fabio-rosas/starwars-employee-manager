package dev.fabianalvarez.starwarsemployee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Response object representing an Employee")
public record EmployeeResponse(

    @Schema(description = "Employee ID", example = "1")
    Long id,

    @Schema(description = "Full name", example = "Luke Skywalker")
    String fullName,

    @Schema(description = "Role", example = "Jedi Knight")
    String role,

    @Schema(description = "Place of origin", example = "Tatooine")
    String place,

    @Schema(description = "Birth date", example = "1977-05-25")
    LocalDate birthDate,

    @Schema(description = "Current age", example = "45")
    Integer age,

    @Schema(description = "Is active", example = "true")
    boolean active
) {

}
