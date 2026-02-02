package com.something.rest.controllers;

import com.something.rest.dtos.request.EmployeeReqDto;
import com.something.rest.dtos.response.EmployeeResDto;
import com.something.rest.entities.Employee;
import com.something.rest.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeResDto> create(@RequestBody EmployeeReqDto employeeReqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employeeReqDto));
    }

    @PutMapping("/{id}")
    public Optional<Employee> update(@PathVariable Long id, @RequestBody EmployeeReqDto employeeReqDto) {
        return employeeService.update(id, employeeReqDto);
    }

    @PatchMapping("/{id}")
    public EmployeeResDto updateName(@PathVariable Long id, String name){
        return employeeService.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.delete(id));
    }
}
