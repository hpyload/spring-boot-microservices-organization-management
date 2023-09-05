package com.employeeservice.service.impl;

import com.employeeservice.dto.APIResponseDto;
import com.employeeservice.dto.DepartmentDto;
import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.entity.Employee;
import com.employeeservice.mapper.EmployeeMapper;
import com.employeeservice.repository.EmployeeRepository;
import com.employeeservice.service.APIClient;
import com.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private APIClient apiClient;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        return employeeMapper.mapToDto(employeeRepository.save(employeeMapper.mapToEntity(employeeDto)));
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = employeeMapper.mapToDto(employee);
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
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