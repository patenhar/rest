package com.something.rest.dtos.request;

import com.something.rest.validation.CreateGroup;
import com.something.rest.validation.UpdateGroup;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.Update;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmployeeReqDto {
    private Long id;

    @NotNull(message = "name is required")
    @NotBlank(message = "name cannot be empty", groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

    @NotNull(message = "age is required", groups = {CreateGroup.class})
    @Min(value = 18, message = "minimum 18 years are required", groups = {CreateGroup.class, UpdateGroup.class})
    @Max(value = 60, message = "age cannot be greater than 60", groups = {CreateGroup.class, UpdateGroup.class})
    private int age;
}
