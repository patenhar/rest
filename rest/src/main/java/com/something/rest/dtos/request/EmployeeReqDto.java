package com.something.rest.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmployeeReqDto {
    private Long id;

    @NotNull(message = "name is required")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "age is required")
    @Min(value = 18, message = "minimum 18 years are required")
    private int age;
}
