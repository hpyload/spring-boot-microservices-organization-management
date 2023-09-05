package com.departmentservice.service.impl;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.entity.Department;
import com.departmentservice.mapper.DepartmentMapper;
import com.departmentservice.repository.DepartmentRepository;
import com.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        return departmentMapper.mapToDto(departmentRepository.save(departmentMapper.mapToEntity(departmentDto)));
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findDepartmentByDepartmentCode(code);
        return departmentMapper.mapToDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream().map(departmentMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).get();
        departmentRepository.delete(department);
    }
}
