package com.departmentservice.mapper;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    private final ModelMapper modelMapper;

    public DepartmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DepartmentDto mapToDto(Department employee) {
        return modelMapper.map(employee, DepartmentDto.class);
    }

    public Department mapToEntity(DepartmentDto departmentDto) {
        return modelMapper.map(departmentDto, Department.class);
    }
}