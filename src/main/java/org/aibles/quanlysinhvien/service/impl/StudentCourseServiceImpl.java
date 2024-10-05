package org.aibles.quanlysinhvien.service.impl;


import org.aibles.quanlysinhvien.dto.StudentCourseRequestDTO;
import org.aibles.quanlysinhvien.dto.StudentCourseResponseDTO;
import org.aibles.quanlysinhvien.entity.StudentCourse;
import org.aibles.quanlysinhvien.entity.StudentCourseId;
import org.aibles.quanlysinhvien.exception.exception.CourseNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.StudentNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.StudentCourseNotFoundException;
import org.aibles.quanlysinhvien.repository.CourseRepository;
import org.aibles.quanlysinhvien.repository.StudentCourseRepository;
import org.aibles.quanlysinhvien.repository.StudentRepository;
import org.aibles.quanlysinhvien.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository,
                                    StudentRepository studentRepository,
                                    CourseRepository courseRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public StudentCourseResponseDTO createStudentCourse(StudentCourseRequestDTO studentCourseRequestDTO) {
        // Kiểm tra xem studentId có tồn tại trong bảng Student không
        checkStudentExists(studentCourseRequestDTO.getStudentId());

        // Kiểm tra xem courseId có tồn tại trong bảng Course không
        checkCourseExists(studentCourseRequestDTO.getCourseId());

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentId(studentCourseRequestDTO.getStudentId());
        studentCourse.setCourseId(studentCourseRequestDTO.getCourseId());

        StudentCourse savedStudentCourse = studentCourseRepository.save(studentCourse);

        return mapToStudentCourseResponseDTO(savedStudentCourse);
    }

    @Override
    public List<StudentCourseResponseDTO> getAllStudentCourses() {
        List<StudentCourse> studentCourses = studentCourseRepository.findAll();
        return studentCourses.stream().map(this::mapToStudentCourseResponseDTO).collect(Collectors.toList());
    }

    @Override
    public StudentCourseResponseDTO getStudentCourseById(int studentId, int courseId) {
        StudentCourseId studentCourseId = new StudentCourseId(studentId, courseId);
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId)
                .orElseThrow(() -> new StudentCourseNotFoundException("StudentCourse with student ID " + studentId + " and course ID " + courseId + " ."));
        return mapToStudentCourseResponseDTO(studentCourse);
    }

    @Override
    public void deleteStudentCourse(int studentId, int courseId) {
        StudentCourseId studentCourseId = new StudentCourseId(studentId, courseId);
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId)
                .orElseThrow(() -> new StudentCourseNotFoundException("StudentCourse with student ID " + studentId + " and course ID " + courseId + " not found."));
        studentCourseRepository.delete(studentCourse);
    }

    // Kiểm tra xem studentId có tồn tại trong bảng Student không
    private void checkStudentExists(Integer studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException("Student with ID " + studentId + " does not exist.");
        }
    }

    // Kiểm tra xem courseId có tồn tại trong bảng Course không
    private void checkCourseExists(Integer courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException("Course with ID " + courseId + " does not exist.");
        }
    }


    private StudentCourseResponseDTO mapToStudentCourseResponseDTO(StudentCourse studentCourse) {
        StudentCourseResponseDTO studentCourseResponseDTO = new StudentCourseResponseDTO();
        studentCourseResponseDTO.setStudentId(studentCourse.getStudentId());
        studentCourseResponseDTO.setCourseId(studentCourse.getCourseId());
        return studentCourseResponseDTO;
    }
}

