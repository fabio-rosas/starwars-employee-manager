package dev.fabianalvarez.starwarsemployee.service;

import dev.fabianalvarez.starwarsemployee.dto.EmployeeRequest;
import dev.fabianalvarez.starwarsemployee.dto.EmployeeResponse;
import java.util.List;

public interface IEmployeeService {

  EmployeeResponse save(EmployeeRequest request);

  List<EmployeeResponse> getAll();

  EmployeeResponse getById(Long id);

  EmployeeResponse update(Long id, EmployeeRequest request);

  void delete(Long id);
}
