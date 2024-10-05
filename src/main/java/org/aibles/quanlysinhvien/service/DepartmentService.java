package org.aibles.quanlysinhvien.service;


import org.aibles.quanlysinhvien.dto.DepartmentRequestDTO;
import org.aibles.quanlysinhvien.dto.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);

    List<DepartmentResponseDTO> getAllDepartments();

    DepartmentResponseDTO getDepartmentById(int departmentId);

    DepartmentResponseDTO updateDepartment(int departmentId, DepartmentRequestDTO departmentRequestDTO);

    void deleteDepartment(int departmentId);
}

