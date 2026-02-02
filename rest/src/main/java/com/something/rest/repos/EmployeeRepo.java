package com.something.rest.repos;

import com.something.rest.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Employee e SET e.name = :name WHERE e.id = :id")
    int updateEmployeeName(@Param("id") Long id, @Param("name") String name);
}
