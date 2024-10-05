package org.aibles.quanlysinhvien.controller;


import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.dto.BaseResponse;
import org.aibles.quanlysinhvien.dto.DepartmentRequestDTO;
import org.aibles.quanlysinhvien.dto.DepartmentResponseDTO;
import org.aibles.quanlysinhvien.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // API tạo mới một department
    @PostMapping
    public ResponseEntity<BaseResponse<DepartmentResponseDTO>> createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.createDepartment(departmentRequestDTO);
        BaseResponse<DepartmentResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                departmentResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API lấy tất cả departments
    @GetMapping
    public ResponseEntity<BaseResponse<List<DepartmentResponseDTO>>> getAllDepartments() {
        List<DepartmentResponseDTO> departments = departmentService.getAllDepartments();
        BaseResponse<List<DepartmentResponseDTO>> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                departments
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API lấy department theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<DepartmentResponseDTO>> getDepartmentById(@PathVariable int id) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.getDepartmentById(id);
        BaseResponse<DepartmentResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                departmentResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API cập nhật department
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<DepartmentResponseDTO>> updateDepartment(
            @PathVariable int id,
            @RequestBody DepartmentRequestDTO departmentRequestDTO
    ) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.updateDepartment(id, departmentRequestDTO);
        BaseResponse<DepartmentResponseDTO> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                departmentResponseDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // API xóa department
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        BaseResponse<Void> response = new BaseResponse<>(
                ResponseCode.SUCCESS,
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

