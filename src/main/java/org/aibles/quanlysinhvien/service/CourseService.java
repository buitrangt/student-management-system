package org.aibles.quanlysinhvien.service;


import org.aibles.quanlysinhvien.dto.CourseResponseDTO;
import org.aibles.quanlysinhvien.dto.CourseRequestDTO;

import java.util.List;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);

    List<CourseResponseDTO> getAllCourses();

    CourseResponseDTO getCourseById(int courseId);

    CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO);

    void deleteCourse(int courseId);
}

