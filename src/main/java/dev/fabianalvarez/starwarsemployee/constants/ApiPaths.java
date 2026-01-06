package dev.fabianalvarez.starwarsemployee.constants;

/**
 * Defines centralized paths for API endpoints to avoid hardcoding and improve maintainability.
 */
public class ApiPaths {

  private ApiPaths() {
    // Utility class
  }

  public static final String EMPLOYEES = "/employees";
  public static final String BY_ID = "/{id}";
}
