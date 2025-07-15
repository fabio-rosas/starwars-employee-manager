package dev.fabianalvarez.starwarsemployee.controller;

import dev.fabianalvarez.starwarsemployee.constants.ApiPaths;
import dev.fabianalvarez.starwarsemployee.constants.SwaggerDoc;
import dev.fabianalvarez.starwarsemployee.dto.EmployeeRequest;
import dev.fabianalvarez.starwarsemployee.dto.EmployeeResponse;
import dev.fabianalvarez.starwarsemployee.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller to manage employees in the Star Wars Employee API.
 */
@RestController
@RequestMapping(ApiPaths.EMPLOYEES)
@Tag(name = SwaggerDoc.TAG_EMPLOYEE)
public class EmployeeController {

  private final IEmployeeService employeeService;

  @Autowired
  public EmployeeController(IEmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping
  @Operation(summary = SwaggerDoc.CREATE_SUMMARY)
  @ApiResponse(responseCode = "201", description = SwaggerDoc.CREATE_DESCRIPTION)
  public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
    return new ResponseEntity<>(employeeService.save(request), HttpStatus.CREATED);
  }

  @GetMapping
  @Operation(summary = SwaggerDoc.GET_ALL_SUMMARY)
  @ApiResponse(responseCode = "200", description = SwaggerDoc.GET_ALL_DESCRIPTION)
  public ResponseEntity<List<EmployeeResponse>> getAll() {
    return ResponseEntity.ok(employeeService.getAll());
  }

  @GetMapping(ApiPaths.BY_ID)
  @Operation(summary = SwaggerDoc.GET_BY_ID_SUMMARY)
  @ApiResponse(responseCode = "200", description = SwaggerDoc.GET_BY_ID_DESCRIPTION)
  public ResponseEntity<EmployeeResponse> getById(
      @Parameter(description = SwaggerDoc.PARAM_EMPLOYEE_ID, example = "1")
      @PathVariable Long id) {
    return ResponseEntity.ok(employeeService.getById(id));
  }

  @PutMapping(ApiPaths.BY_ID)
  @Operation(summary = SwaggerDoc.UPDATE_SUMMARY)
  @ApiResponse(responseCode = "200", description = SwaggerDoc.UPDATE_DESCRIPTION)
  public ResponseEntity<EmployeeResponse> update(
      @Parameter(description = SwaggerDoc.PARAM_EMPLOYEE_ID, example = "1")
      @PathVariable Long id,
      @Valid @RequestBody EmployeeRequest request) {
    return ResponseEntity.ok(employeeService.update(id, request));
  }

  @DeleteMapping(ApiPaths.BY_ID)
  @Operation(summary = SwaggerDoc.DELETE_SUMMARY)
  @ApiResponse(responseCode = "204", description = SwaggerDoc.DELETED_DESCRIPTION)
  public ResponseEntity<Void> delete(
      @Parameter(description = SwaggerDoc.PARAM_EMPLOYEE_ID, example = "1")
      @PathVariable Long id) {
    employeeService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
