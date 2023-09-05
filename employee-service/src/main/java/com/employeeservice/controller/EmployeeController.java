package com.employeeservice.controller;

import com.employeeservice.dto.APIResponseDto;
import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createDepartment(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping( "/{employeeId}")
    public ResponseEntity<APIResponseDto> getDepartmentByCode(@PathVariable(value = "employeeId") Long EmployeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(EmployeeId),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllDepartment() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable(value = "employeeId") Long employeeId) {
        employeeService.deleteEmployeesById(employeeId);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}
