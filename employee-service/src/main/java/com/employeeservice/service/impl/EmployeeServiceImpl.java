package com.employeeservice.service.impl;

import com.employeeservice.dto.ApiResponseDto;
import com.employeeservice.dto.DepartmentDto;
import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.entity.Employee;
import com.employeeservice.mapper.EmployeeMapper;
import com.employeeservice.repository.EmployeeRepository;
import com.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private WebClient webClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        return employeeMapper.mapToDto(employeeRepository.save(employeeMapper.mapToEntity(employeeDto)));
    }

    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();

        // Create an object to store the HTTP response
        DepartmentDto departmentDto = webClient.get()
                // URI for the HTTP request
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                // Trigger the HTTP request and retrieve the response
                .retrieve()
                // Treat the response body as a DepartmentDto object
                .bodyToMono(DepartmentDto.class)
                // Block the current thread while waiting for the response
                .block();

        EmployeeDto employeeDto = employeeMapper.mapToDto(employee);

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(employeeMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeesById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
    }
}