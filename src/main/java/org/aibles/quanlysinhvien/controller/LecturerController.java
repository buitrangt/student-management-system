package org.aibles.quanlysinhvien.controller;


import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.dto.BaseResponse;
import org.aibles.quanlysinhvien.dto.LecturerRequestDTO;
import org.aibles.quanlysinhvien.dto.LecturerResponseDTO;
import org.aibles.quanlysinhvien.service.LecturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lecturers")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    // API tạo mới một Lecturer
    @PostMapping
    public ResponseEntity<BaseResponse<LecturerResponseDTO>> createLecturer(@RequestBody LecturerRequestDTO lecturerRequestDTO) {
        LecturerResponseDTO lecturerResponseDTO = lecturerService.createLecturer(lecturerRequestDTO);
        BaseResponse<LecturerResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                lecturerResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API lấy tất cả Lecturers
    @GetMapping
    public ResponseEntity<BaseResponse<List<LecturerResponseDTO>>> getAllLecturers() {
        List<LecturerResponseDTO> lecturers = lecturerService.getAllLecturers();
        BaseResponse<List<LecturerResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                lecturers
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API lấy Lecturer theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<LecturerResponseDTO>> getLecturerById(@PathVariable int id) {
        LecturerResponseDTO lecturerResponseDTO = lecturerService.getLecturerById(id);
        BaseResponse<LecturerResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                lecturerResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API cập nhật Lecturer
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<LecturerResponseDTO>> updateLecturer(@PathVariable int id, @RequestBody LecturerRequestDTO lecturerRequestDTO) {
        LecturerResponseDTO lecturerResponseDTO = lecturerService.updateLecturer(id, lecturerRequestDTO);
        BaseResponse<LecturerResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                lecturerResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API xóa Lecturer
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteLecturer(@PathVariable int id) {
        lecturerService.deleteLecturer(id);
        BaseResponse<Void> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

