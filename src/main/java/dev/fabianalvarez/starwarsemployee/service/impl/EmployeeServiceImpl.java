package dev.fabianalvarez.starwarsemployee.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import dev.fabianalvarez.starwarsemployee.dto.EmployeeRequest;
import dev.fabianalvarez.starwarsemployee.dto.EmployeeResponse;
import dev.fabianalvarez.starwarsemployee.entity.Employee;
import dev.fabianalvarez.starwarsemployee.exception.EmployeeNotFoundException;
import dev.fabianalvarez.starwarsemployee.repository.EmployeeRepository;
import dev.fabianalvarez.starwarsemployee.service.IEmployeeService;
import dev.fabianalvarez.starwarsemployee.utils.MessageKeys;
import dev.fabianalvarez.starwarsemployee.utils.MessagesUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

  private final EmployeeRepository repository;

  @Override
  public EmployeeResponse save(EmployeeRequest request) {
    Employee employee = Objects.requireNonNull(mapToEntity(request), "Employee cannot be null");
    Employee saved = repository.save(employee);
    return mapToResponse(saved);
  }

  @Override
  public List<EmployeeResponse> getAll() {
    return repository.findAll().stream()
        .map(this::mapToResponse)
        .toList();
  }

  @Override
  public EmployeeResponse getById(@NonNull Long id) {
    Employee employee = repository.findById(id)
        .orElseThrow(() ->
            new EmployeeNotFoundException(MessagesUtils.getMessage(MessageKeys.EMPLOYEE_NOT_FOUND, id))
        );
    return mapToResponse(employee);
  }

  @Override
  public EmployeeResponse update(@NonNull Long id, @NonNull EmployeeRequest request) {
    Employee existing = repository.findById(id)
        .orElseThrow(() ->
            new EmployeeNotFoundException(MessagesUtils.getMessage(MessageKeys.EMPLOYEE_NOT_FOUND, id))
        );

    existing.setFirstName(request.firstName());
    existing.setLastName(request.lastName());
    existing.setRole(request.role());
    existing.setPlace(request.place());
    existing.setBirthDate(request.birthDate());
    existing.setActive(request.active());

    return mapToResponse(repository.save(existing));
  }

  @Override
  public void delete(@NonNull Long id) {
    if (!repository.existsById(id)) {
      throw new EmployeeNotFoundException(MessagesUtils.getMessage(MessageKeys.EMPLOYEE_NOT_FOUND, id));
    }
    repository.deleteById(id);
  }

  // Helper method to map DTO to Entity
  private Employee mapToEntity(@NonNull EmployeeRequest request) {
    return Employee.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .role(request.role())
        .place(request.place())
        .birthDate(request.birthDate())
        .active(request.active())
        .build();
  }

  // Helper method to map Entity to DTO
  private EmployeeResponse mapToResponse(@NonNull Employee employee) {
    String fullName = String.format("%s %s", employee.getFirstName(), employee.getLastName());
    return new EmployeeResponse(
        employee.getId(),
        fullName,
        employee.getRole(),
        employee.getPlace(),
        employee.getBirthDate(),
        calculateAge(employee.getBirthDate()),
        employee.isActive()
    );
  }

  private int calculateAge(@NonNull LocalDate birthDate) {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }

}
