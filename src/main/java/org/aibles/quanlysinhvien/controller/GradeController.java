package org.aibles.quanlysinhvien.controller;


import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.dto.BaseResponse;
import org.aibles.quanlysinhvien.dto.GradeRequestDTO;
import org.aibles.quanlysinhvien.dto.GradeResponseDTO;
import org.aibles.quanlysinhvien.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // API tạo mới một grade
    @PostMapping
    public ResponseEntity<BaseResponse<GradeResponseDTO>> createGrade(@RequestBody GradeRequestDTO gradeRequestDTO) {
        GradeResponseDTO gradeResponseDTO = gradeService.createGrade(gradeRequestDTO);
        BaseResponse<GradeResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                gradeResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API lấy tất cả grades
    @GetMapping
    public ResponseEntity<BaseResponse<List<GradeResponseDTO>>> getAllGrades() {
        List<GradeResponseDTO> grades = gradeService.getAllGrades();
        BaseResponse<List<GradeResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                grades
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API lấy grade theo studentId và courseId
    @GetMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<BaseResponse<GradeResponseDTO>> getGradeById(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId
    ) {
        GradeResponseDTO gradeResponseDTO = gradeService.getGradeById(studentId, courseId);
        BaseResponse<GradeResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                gradeResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API cập nhật grade theo studentId và courseId
    @PutMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<BaseResponse<GradeResponseDTO>> updateGrade(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId,
            @RequestBody GradeRequestDTO gradeRequestDTO
    ) {
        GradeResponseDTO gradeResponseDTO = gradeService.updateGrade(studentId, courseId, gradeRequestDTO);
        BaseResponse<GradeResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                gradeResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API xóa grade theo studentId và courseId
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<BaseResponse<Void>> deleteGrade(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId
    ) {
        gradeService.deleteGrade(studentId, courseId);
        BaseResponse<Void> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

