package com.employeeservice.service;


import com.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// FeignClient library will dynamically create an implementation for this interface
// This code defines a FeignClient interface to communicate with a remote service
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    // This method sends a GET request to the remote service to retrieve a department by its code
    @GetMapping( "api/departments/{departmentCode}")
    DepartmentDto getDepartmentByCode(@PathVariable(value = "departmentCode") String departmentCode);
}
