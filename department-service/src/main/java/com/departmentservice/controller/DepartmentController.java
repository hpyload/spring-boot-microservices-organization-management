package com.departmentservice.controller;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto>createDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping( "/{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable(value = "departmentCode") String departmentCode) {
        return new ResponseEntity<>(departmentService.getDepartmentByCode(departmentCode),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        return new ResponseEntity<>(departmentService.getAllDepartment(), HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable(value = "departmentId") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
    }
}
