package org.aibles.quanlysinhvien.service;


import org.aibles.quanlysinhvien.dto.StudentRequestDTO;
import org.aibles.quanlysinhvien.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(int studentId);

    StudentResponseDTO updateStudent(int studentId, StudentRequestDTO studentRequestDTO);

    void deleteStudent(int studentId);
}

