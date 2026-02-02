package com.something.rest.services;


import com.something.rest.dtos.request.EmployeeReqDto;
import com.something.rest.dtos.response.EmployeeResDto;
import com.something.rest.entities.Employee;
import com.something.rest.repos.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeResDto> getAll() {
        return employeeRepo
                .findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResDto getById(Long id) {
        return modelMapper.map(employeeRepo.findById(id), EmployeeResDto.class);
    }

    @Override
    public EmployeeResDto create(EmployeeReqDto employeeReqDto) {

        Employee e = employeeRepo.save(modelMapper.map(employeeReqDto, Employee.class));
        return modelMapper.map(e, EmployeeResDto.class);
    }

    @Override
    public Optional<Employee> update(Long id, EmployeeReqDto employeeReqDto) {
        return employeeRepo.findById(id).map(employee -> {
            modelMapper.map(employee, EmployeeResDto.class);
            return employeeRepo.save(employee);
        });
    }

    @Override
    public EmployeeResDto updateName(Long id, String name) {
        return modelMapper.map(employeeRepo.updateEmployeeName(id, name), EmployeeResDto.class);
    }

    @Override
    public String delete(Long id) {
        if(employeeRepo.findById(id).isPresent()){
            employeeRepo.deleteById(id);
            return "User deleted successfully";
        }
        return "User not found";
    }
}
