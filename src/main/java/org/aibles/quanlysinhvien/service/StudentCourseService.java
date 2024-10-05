package org.aibles.quanlysinhvien.service;


import org.aibles.quanlysinhvien.dto.StudentCourseRequestDTO;
import org.aibles.quanlysinhvien.dto.StudentCourseResponseDTO;

import java.util.List;

public interface StudentCourseService {

    StudentCourseResponseDTO createStudentCourse(StudentCourseRequestDTO studentCourseRequestDTO);

    List<StudentCourseResponseDTO> getAllStudentCourses();

    StudentCourseResponseDTO getStudentCourseById(int studentId, int courseId);

    void deleteStudentCourse(int studentId, int courseId);
}

