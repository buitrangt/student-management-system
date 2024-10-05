package org.aibles.quanlysinhvien.service;

import org.aibles.quanlysinhvien.dto.ClassRequestDTO;
import org.aibles.quanlysinhvien.dto.ClassResponseDTO;
import org.aibles.quanlysinhvien.repository.ClassRepository;
import org.springframework.stereotype.Service;
import org.aibles.quanlysinhvien.dto.ClassResponseDTO;

import java.util.List;
import org.aibles.quanlysinhvien.dto.ClassResponseDTO;

import java.util.List;

public interface ClassService {

    ClassResponseDTO createClass(ClassRequestDTO classRequestDTO);

    List<ClassResponseDTO> getAllClasses();

    ClassResponseDTO getClassById(int classId);

    ClassResponseDTO updateClass(int classId, ClassRequestDTO classRequestDTO);

    void deleteClass(int classId);

    List<ClassResponseDTO> searchClassesByName(String className);  // Thêm phương thức này
}



