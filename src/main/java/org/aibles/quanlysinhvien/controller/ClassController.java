package org.aibles.quanlysinhvien.controller;

import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.dto.ClassRequestDTO;
import org.aibles.quanlysinhvien.dto.ClassResponseDTO;
import org.aibles.quanlysinhvien.dto.BaseResponse;
import org.aibles.quanlysinhvien.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    // API tạo mới lớp học
    @PostMapping
    public ResponseEntity<BaseResponse<ClassResponseDTO>> createClass(@RequestBody ClassRequestDTO classRequestDTO) {
        ClassResponseDTO classResponseDTO = classService.createClass(classRequestDTO);
        BaseResponse<ClassResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS, // Mã phản hồi thành công
                System.currentTimeMillis(),
                classResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Trả về mã 201 Created
    }

    // API lấy tất cả các lớp học
    @GetMapping
    public ResponseEntity<BaseResponse<List<ClassResponseDTO>>> getAllClasses() {
        List<ClassResponseDTO> classes = classService.getAllClasses();
        BaseResponse<List<ClassResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                classes
        );
        return new ResponseEntity<>(response, HttpStatus.OK); // Trả về mã 200 OK
    }

    // API lấy một lớp học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ClassResponseDTO>> getClassById(@PathVariable int id) {
        ClassResponseDTO classResponseDTO = classService.getClassById(id);
        BaseResponse<ClassResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                classResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK); // Trả về mã 200 OK
    }

    // API cập nhật lớp học
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ClassResponseDTO>> updateClass(
            @PathVariable int id,
            @RequestBody ClassRequestDTO classRequestDTO
    ) {
        ClassResponseDTO classResponseDTO = classService.updateClass(id, classRequestDTO);
        BaseResponse<ClassResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                classResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK); // Trả về mã 200 OK
    }

    // API xóa lớp học
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteClass(@PathVariable int id) {
        classService.deleteClass(id);
        BaseResponse<Void> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // Trả về mã 204 No Content
    }

    // API tìm kiếm lớp học theo tên
    @GetMapping("/search")
    public ResponseEntity<BaseResponse<List<ClassResponseDTO>>> searchClasses(@RequestParam String className) {
        List<ClassResponseDTO> classes = classService.searchClassesByName(className);
        BaseResponse<List<ClassResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                classes
        );
        return new ResponseEntity<>(response, HttpStatus.OK); // Trả về mã 200 OK
    }
}
