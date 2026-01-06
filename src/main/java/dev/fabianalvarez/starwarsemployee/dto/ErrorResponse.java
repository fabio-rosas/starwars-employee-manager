package dev.fabianalvarez.starwarsemployee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Standard error response DTO for the API.
 */
@Schema(description = "Error response object")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(

    @Schema(description = "HTTP status code", example = "404")
    int status,

    @Schema(description = "Error message", example = "Employee not found")
    String message,

    @Schema(description = "Request path", example = "/employees/1")
    String path,

    @Schema(description = "Timestamp of the error", example = "2026-01-05T18:33:05.149")
    LocalDateTime timestamp,

    @Schema(description = "Validation errors (if applicable)")
    List<FieldError> errors
) {

  /**
   * DTO for field validation errors.
   */
  public record FieldError(
      @Schema(description = "Field name", example = "firstName")
      String field,

      @Schema(description = "Error message", example = "First name cannot be empty")
      String message
  ) {}
}
