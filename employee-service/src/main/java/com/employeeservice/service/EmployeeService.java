package com.employeeservice.service;

import com.employeeservice.dto.ApiResponseDto;
import com.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    ApiResponseDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
    void deleteEmployeesById(Long id);
}