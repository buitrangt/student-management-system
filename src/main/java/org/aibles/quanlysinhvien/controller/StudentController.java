package org.aibles.quanlysinhvien.controller;


import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.dto.BaseResponse;
import org.aibles.quanlysinhvien.dto.StudentRequestDTO;
import org.aibles.quanlysinhvien.dto.StudentResponseDTO;
import org.aibles.quanlysinhvien.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // API tạo mới một Student
    @PostMapping
    public ResponseEntity<BaseResponse<StudentResponseDTO>> createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO studentResponseDTO = studentService.createStudent(studentRequestDTO);
        BaseResponse<StudentResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                studentResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API lấy tất cả Students
    @GetMapping
    public ResponseEntity<BaseResponse<List<StudentResponseDTO>>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        BaseResponse<List<StudentResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                students
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API lấy Student theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<StudentResponseDTO>> getStudentById(@PathVariable int id) {
        StudentResponseDTO studentResponseDTO = studentService.getStudentById(id);
        BaseResponse<StudentResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                studentResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API cập nhật Student
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<StudentResponseDTO>> updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO studentResponseDTO = studentService.updateStudent(id, studentRequestDTO);
        BaseResponse<StudentResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                studentResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API xóa Student
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        BaseResponse<Void> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

