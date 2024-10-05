package org.aibles.quanlysinhvien.controller;


import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.dto.CourseRequestDTO;
import org.aibles.quanlysinhvien.dto.CourseResponseDTO;
import org.aibles.quanlysinhvien.dto.BaseResponse;
import org.aibles.quanlysinhvien.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // API tạo mới một khóa học
    @PostMapping
    public ResponseEntity<BaseResponse<CourseResponseDTO>> createCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO courseResponseDTO = courseService.createCourse(courseRequestDTO);
        BaseResponse<CourseResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                courseResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API lấy tất cả các khóa học
    @GetMapping
    public ResponseEntity<BaseResponse<List<CourseResponseDTO>>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        BaseResponse<List<CourseResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                courses
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API lấy một khóa học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CourseResponseDTO>> getCourseById(@PathVariable int id) {
        CourseResponseDTO courseResponseDTO = courseService.getCourseById(id);
        BaseResponse<CourseResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                courseResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API cập nhật khóa học theo ID
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<CourseResponseDTO>> updateCourse(
            @PathVariable int id,
            @RequestBody CourseRequestDTO courseRequestDTO
    ) {
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(id, courseRequestDTO);
        BaseResponse<CourseResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                courseResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API xóa một khóa học theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        BaseResponse<Void> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

