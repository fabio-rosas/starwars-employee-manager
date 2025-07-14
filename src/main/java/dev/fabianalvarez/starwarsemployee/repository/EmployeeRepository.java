package dev.fabianalvarez.starwarsemployee.repository;

import dev.fabianalvarez.starwarsemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
