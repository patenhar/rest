package com.something.rest.controllers;

import com.something.rest.dtos.request.EmployeeReqDto;
import com.something.rest.dtos.response.EmployeeResDto;
import com.something.rest.entities.Employee;
import com.something.rest.services.EmployeeService;
import com.something.rest.utils.ApiResponse;
import com.something.rest.validation.CreateGroup;
import com.something.rest.validation.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Tag(name = "Employee")
    @Tag(name = "Get")
    @Operation(summary = "get all employees")
    @GetMapping(value = "/", params = "version=1")
    public ResponseEntity<ApiResponse<List<EmployeeResDto>>> getAll(@RequestParam(name = "version") int version){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", employeeService.getAll()));
    }

    @Tag(name = "Employee")
    @Tag(name = "Get")
    @Operation(summary = "get employee from id")
    @GetMapping(value = "/{id}", headers = "X-API-VERSION=1")
    public ResponseEntity<ApiResponse<EmployeeResDto>> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", employeeService.getById(id)));
    }

    @Tag(name = "Employee")
    @Tag(name = "Create")
    @Operation(summary = "create an employee")
    @PostMapping(value = "/", produces = "application/vnd.company.app-v1+json")
    public ResponseEntity<ApiResponse<EmployeeResDto>> create(@Valid @RequestBody EmployeeReqDto employeeReqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success", employeeService.create(employeeReqDto)));
    }

    @Tag(name = "Employee")
    @Tag(name = "Update")
    @Operation(summary = "update employee details")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResDto>> update(@PathVariable @NotNull Long id, @Validated(UpdateGroup.class) @RequestBody EmployeeReqDto employeeReqDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.update(id, employeeReqDto)));
    }

    @Tag(name = "Employee")
    @Tag(name = "Update")
    @Operation(summary = "update employee name")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResDto>> updateName(@PathVariable @NotNull Long id, @RequestParam @NotBlank String name){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", employeeService.updateName(id, name)));
    }

    @Tag(name = "Employee")
    @Tag(name = "Delete")
    @Operation(summary = "delete an employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable @NotNull Long id) {
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", null));
    }
}
