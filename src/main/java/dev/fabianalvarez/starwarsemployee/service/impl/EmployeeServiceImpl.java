package dev.fabianalvarez.starwarsemployee.service.impl;

import dev.fabianalvarez.starwarsemployee.dto.EmployeeRequest;
import dev.fabianalvarez.starwarsemployee.dto.EmployeeResponse;
import dev.fabianalvarez.starwarsemployee.entity.Employee;
import dev.fabianalvarez.starwarsemployee.repository.EmployeeRepository;
import dev.fabianalvarez.starwarsemployee.service.IEmployeeService;
import dev.fabianalvarez.starwarsemployee.utils.MessageKeys;
import dev.fabianalvarez.starwarsemployee.utils.MessagesUtils;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

  private final EmployeeRepository repository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository repository) {
    this.repository = repository;
  }

  @Override
  public EmployeeResponse save(EmployeeRequest request) {
    Employee employee = mapToEntity(request);
    Employee saved = repository.save(employee);
    return mapToResponse(saved);
  }

  @Override
  public List<EmployeeResponse> getAll() {
    return repository.findAll().stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public EmployeeResponse getById(Long id) {
    Employee employee = repository.findById(id)
        .orElseThrow(() ->
            new RuntimeException(MessagesUtils.getMessage(MessageKeys.EMPLOYEE_NOT_FOUND, id))
        );
    return mapToResponse(employee);
  }

  @Override
  public EmployeeResponse update(Long id, EmployeeRequest request) {
    Employee existing = repository.findById(id)
        .orElseThrow(() ->
            new RuntimeException(MessagesUtils.getMessage(MessageKeys.EMPLOYEE_NOT_FOUND, id))
        );

    existing.setFirstName(request.firstName());
    existing.setLastName(request.lastName());
    existing.setPlace(request.place());
    existing.setBirthDate(request.birthDate());

    return mapToResponse(repository.save(existing));
  }

  @Override
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new RuntimeException(MessagesUtils.getMessage(MessageKeys.EMPLOYEE_NOT_FOUND, id));
    }
    repository.deleteById(id);
  }

  // Helper method to map DTO to Entity
  private Employee mapToEntity(EmployeeRequest request) {
    return Employee.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .place(request.place())
        .birthDate(request.birthDate())
        .build();
  }

  // Helper method to map Entity to DTO
  private EmployeeResponse mapToResponse(Employee employee) {
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

  private int calculateAge(LocalDate birthDate) {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }

}
