package com.something.rest.controllers;

import com.something.rest.dtos.request.EmployeeReqDto;
import com.something.rest.dtos.response.EmployeeResDto;
import com.something.rest.entities.Employee;
import com.something.rest.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Tag(name = "Employee")
    @Tag(name = "Get")
    @Operation(summary = "get all employees")
    @GetMapping(value = "/", params = "version=1")
    public ResponseEntity<List<EmployeeResDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAll());
    }

    @Tag(name = "Employee")
    @Tag(name = "Get")
    @Operation(summary = "get employee from id")
    @GetMapping(value = "/{id}", headers = "X-API-VERSION=1")
    public ResponseEntity<EmployeeResDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getById(id));
    }

    @Tag(name = "Employee")
    @Tag(name = "Create")
    @Operation(summary = "create an employee")
    @PostMapping(value = "/", produces = "application/vnd.company.app-v1+json")
    public ResponseEntity<EmployeeResDto> create(@Valid @RequestBody EmployeeReqDto employeeReqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employeeReqDto));
    }

    @Tag(name = "Employee")
    @Tag(name = "Update")
    @Operation(summary = "update employee details")
    @PutMapping("/{id}")
    public Optional<Employee> update(@PathVariable Long id, @RequestBody EmployeeReqDto employeeReqDto) {
        return employeeService.update(id, employeeReqDto);
    }

    @Tag(name = "Employee")
    @Tag(name = "Update")
    @Operation(summary = "update employee name")
    @PatchMapping("/{id}")
    public EmployeeResDto updateName(@PathVariable Long id, String name){
        return employeeService.updateName(id, name);
    }

    @Tag(name = "Employee")
    @Tag(name = "Delete")
    @Operation(summary = "delete an employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.delete(id));
    }
}
