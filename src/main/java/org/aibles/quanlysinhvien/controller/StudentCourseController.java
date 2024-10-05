package org.aibles.quanlysinhvien.controller;


import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.dto.BaseResponse;
import org.aibles.quanlysinhvien.dto.StudentCourseRequestDTO;
import org.aibles.quanlysinhvien.dto.StudentCourseResponseDTO;
import org.aibles.quanlysinhvien.service.StudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student-courses")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    // API tạo mới một StudentCourse
    @PostMapping
    public ResponseEntity<BaseResponse<StudentCourseResponseDTO>> createStudentCourse(@RequestBody StudentCourseRequestDTO studentCourseRequestDTO) {
        StudentCourseResponseDTO studentCourseResponseDTO = studentCourseService.createStudentCourse(studentCourseRequestDTO);
        BaseResponse<StudentCourseResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                studentCourseResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API lấy tất cả StudentCourses
    @GetMapping
    public ResponseEntity<BaseResponse<List<StudentCourseResponseDTO>>> getAllStudentCourses() {
        List<StudentCourseResponseDTO> studentCourses = studentCourseService.getAllStudentCourses();
        BaseResponse<List<StudentCourseResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                studentCourses
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API lấy StudentCourse theo studentId và courseId
    @GetMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<BaseResponse<StudentCourseResponseDTO>> getStudentCourseById(
            @PathVariable int studentId,
            @PathVariable int courseId
    ) {
        StudentCourseResponseDTO studentCourseResponseDTO = studentCourseService.getStudentCourseById(studentId, courseId);
        BaseResponse<StudentCourseResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                studentCourseResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API xóa StudentCourse theo studentId và courseId
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<BaseResponse<Void>> deleteStudentCourse(
            @PathVariable int studentId,
            @PathVariable int courseId
    ) {
        studentCourseService.deleteStudentCourse(studentId, courseId);
        BaseResponse<Void> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

