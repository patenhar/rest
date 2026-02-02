package com.something.rest.services;

import com.something.rest.dtos.request.EmployeeReqDto;
import com.something.rest.dtos.response.EmployeeResDto;
import com.something.rest.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<EmployeeResDto> getAll();
    EmployeeResDto getById(Long id);
    EmployeeResDto create(EmployeeReqDto employeeReqDto);
    Optional<Employee> update(Long id, EmployeeReqDto employeeReqDto);
    EmployeeResDto updateName(Long id, String name);

    String delete(Long id);
}

