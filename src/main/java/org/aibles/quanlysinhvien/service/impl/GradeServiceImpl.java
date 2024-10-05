package org.aibles.quanlysinhvien.service.impl;


import org.aibles.quanlysinhvien.dto.GradeRequestDTO;
import org.aibles.quanlysinhvien.dto.GradeResponseDTO;
import org.aibles.quanlysinhvien.entity.Grade;
import org.aibles.quanlysinhvien.entity.GradeId;
import org.aibles.quanlysinhvien.exception.exception.GradeNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.InvalidRequestException;
import org.aibles.quanlysinhvien.exception.exception.StudentNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.CourseNotFoundException;
import org.aibles.quanlysinhvien.repository.CourseRepository;
import org.aibles.quanlysinhvien.repository.GradeRepository;
import org.aibles.quanlysinhvien.repository.StudentRepository;
import org.aibles.quanlysinhvien.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Tạo mới một Grade
    @Override
    public GradeResponseDTO createGrade(GradeRequestDTO gradeRequestDTO) {
        // Kiểm tra tính hợp lệ của request
        validateGradeRequest(gradeRequestDTO);

        // Kiểm tra xem studentId và courseId có tồn tại trong cơ sở dữ liệu không
        checkStudentExists(gradeRequestDTO.getStudentId());
        checkCourseExists(gradeRequestDTO.getCourseId());

        // Tạo một đối tượng Grade mới
        Grade grade = new Grade();
        grade.setStudentId(gradeRequestDTO.getStudentId());
        grade.setCourseId(gradeRequestDTO.getCourseId());
        grade.setScore(gradeRequestDTO.getScore());

        // Lưu đối tượng Grade vào cơ sở dữ liệu
        Grade savedGrade = gradeRepository.save(grade);

        return mapToGradeResponseDTO(savedGrade);
    }

    // Lấy tất cả Grades
    @Override
    public List<GradeResponseDTO> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        return grades.stream().map(this::mapToGradeResponseDTO).collect(Collectors.toList());
    }

    // Lấy Grade theo studentId và courseId
    @Override
    public GradeResponseDTO getGradeById(Integer studentId, Integer courseId) {
        GradeId gradeId = new GradeId(studentId, courseId);
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new GradeNotFoundException("Grade with student ID " + studentId + " and course ID " + courseId + " not found."));
        return mapToGradeResponseDTO(grade);
    }

    // Cập nhật Grade theo studentId và courseId
    @Override
    public GradeResponseDTO updateGrade(Integer studentId, Integer courseId, GradeRequestDTO gradeRequestDTO) {
        // Kiểm tra tính hợp lệ của request
        validateGradeRequest(gradeRequestDTO);

        GradeId gradeId = new GradeId(studentId, courseId);
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new GradeNotFoundException("Grade with student ID " + studentId + " and course ID " + courseId + " not found."));

        // Cập nhật giá trị score
        grade.setScore(gradeRequestDTO.getScore());

        // Lưu đối tượng Grade sau khi cập nhật
        Grade updatedGrade = gradeRepository.save(grade);
        return mapToGradeResponseDTO(updatedGrade);
    }

    // Xóa Grade theo studentId và courseId
    @Override
    public void deleteGrade(Integer studentId, Integer courseId) {
        GradeId gradeId = new GradeId(studentId, courseId);
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new GradeNotFoundException("Grade with student ID " + studentId + " and course ID " + courseId + " not found."));

        // Xóa Grade khỏi cơ sở dữ liệu
        gradeRepository.delete(grade);
    }

    // Kiểm tra tính hợp lệ của GradeRequestDTO
    private void validateGradeRequest(GradeRequestDTO gradeRequestDTO) {
        if (gradeRequestDTO.getStudentId() == null || gradeRequestDTO.getCourseId() == null) {
            throw new InvalidRequestException("Student ID and Course ID must not be null.");
        }
        if (gradeRequestDTO.getScore() == null) {
            throw new InvalidRequestException("Score must not be null.");
        }
    }

    // Kiểm tra xem studentId có tồn tại trong bảng Student không
    private void checkStudentExists(Integer studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found.");
        }
    }

    // Kiểm tra xem courseId có tồn tại trong bảng Course không
    private void checkCourseExists(Integer courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found.");
        }
    }

    // Phương thức chuyển đổi từ Grade entity sang GradeResponseDTO
    private GradeResponseDTO mapToGradeResponseDTO(Grade grade) {
        GradeResponseDTO gradeResponseDTO = new GradeResponseDTO();
        gradeResponseDTO.setStudentId(grade.getStudentId());
        gradeResponseDTO.setCourseId(grade.getCourseId());
        gradeResponseDTO.setScore(grade.getScore());
        return gradeResponseDTO;
    }
}

