package dev.fabianalvarez.starwarsemployee.constants;

/**
 * Provides Swagger documentation constants for endpoints and operations.
 */
public class SwaggerDoc {

  private SwaggerDoc() {
    // Utility class
  }

  // ====== Tags ======
  public static final String TAG_EMPLOYEE = "Employees";

  // ====== Summaries ======
  public static final String CREATE_SUMMARY = "Create a new employee";
  public static final String GET_ALL_SUMMARY = "Get all employees";
  public static final String GET_BY_ID_SUMMARY = "Get an employee by ID";
  public static final String UPDATE_SUMMARY = "Update an existing employee";
  public static final String DELETE_SUMMARY = "Delete an employee by ID";

  // ====== Descriptions ======
  public static final String CREATE_DESCRIPTION = "Employee successfully created";
  public static final String GET_ALL_DESCRIPTION = "List of employees retrieved";
  public static final String GET_BY_ID_DESCRIPTION = "Employee retrieved successfully";
  public static final String UPDATE_DESCRIPTION = "Employee updated successfully";
  public static final String DELETED_DESCRIPTION = "Employee deleted successfully";

  // ====== Parameter Descriptions ======
  public static final String PARAM_EMPLOYEE_ID = "Employee ID";

}
