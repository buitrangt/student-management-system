package org.aibles.quanlysinhvien.service.impl;

import org.aibles.quanlysinhvien.dto.DepartmentRequestDTO;
import org.aibles.quanlysinhvien.dto.DepartmentResponseDTO;
import org.aibles.quanlysinhvien.entity.Department;
import org.aibles.quanlysinhvien.exception.exception.DepartmentNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.InvalidRequestException;
import org.aibles.quanlysinhvien.repository.DepartmentRepository;
import org.aibles.quanlysinhvien.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        // Kiểm tra tính hợp lệ của request
        validateDepartmentRequest(departmentRequestDTO);

        Department department = new Department();
        department.setDepartmentName(departmentRequestDTO.getDepartmentName());

        Department savedDepartment = departmentRepository.save(department);

        return mapToDepartmentResponseDTO(savedDepartment);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()) {
            throw new DepartmentNotFoundException("No departments found.");
        }
        return departments.stream().map(this::mapToDepartmentResponseDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found."));
        return mapToDepartmentResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(int departmentId, DepartmentRequestDTO departmentRequestDTO) {
        // Kiểm tra tính hợp lệ của request
        validateDepartmentRequest(departmentRequestDTO);

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found."));

        department.setDepartmentName(departmentRequestDTO.getDepartmentName());

        Department updatedDepartment = departmentRepository.save(department);
        return mapToDepartmentResponseDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + departmentId + " not found."));

        departmentRepository.delete(department);
    }

    // Phương thức kiểm tra tính hợp lệ của request
    private void validateDepartmentRequest(DepartmentRequestDTO departmentRequestDTO) {
        if (!StringUtils.hasText(departmentRequestDTO.getDepartmentName())) {
            throw new InvalidRequestException("Department name must not be empty.");
        }
    }

    // Phương thức map từ entity sang DTO
    private DepartmentResponseDTO mapToDepartmentResponseDTO(Department department) {
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setDepartmentId(department.getDepartmentId());
        departmentResponseDTO.setDepartmentName(department.getDepartmentName());
        return departmentResponseDTO;
    }
}
