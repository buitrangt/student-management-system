package org.aibles.quanlysinhvien.service.impl;


import org.aibles.quanlysinhvien.dto.ClassRequestDTO;
import org.aibles.quanlysinhvien.dto.ClassResponseDTO;
import org.aibles.quanlysinhvien.dto.DepartmentResponseDTO;
import org.aibles.quanlysinhvien.entity.ClassEntity;
import org.aibles.quanlysinhvien.entity.Department;
import org.aibles.quanlysinhvien.exception.exception.ClassNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.DepartmentNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.InvalidRequestException;
import org.aibles.quanlysinhvien.repository.ClassRepository;
import org.aibles.quanlysinhvien.repository.DepartmentRepository;
import org.aibles.quanlysinhvien.service.ClassService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final DepartmentRepository departmentRepository;

    public ClassServiceImpl(ClassRepository classRepository, DepartmentRepository departmentRepository) {
        this.classRepository = classRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ClassResponseDTO createClass(ClassRequestDTO classRequestDTO) {
        // Kiểm tra nếu các trường trong request trống hoặc không hợp lệ
        if (!StringUtils.hasText(classRequestDTO.getClassName()) || classRequestDTO.getDepartmentId() == null) {
            throw new InvalidRequestException("Class name and Department ID must not be empty");
        }

        // Tìm department từ departmentId
        Department department = departmentRepository.findById(classRequestDTO.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        // Tạo mới class entity từ dữ liệu request
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassName(classRequestDTO.getClassName());
        classEntity.setDepartmentId(classRequestDTO.getDepartmentId());

        // Lưu class entity vào cơ sở dữ liệu
        ClassEntity savedClass = classRepository.save(classEntity);

        // Trả về response DTO cho class và department
        return mapToClassResponseDTO(savedClass, department);
    }

    @Override
    public ClassResponseDTO getClassById(int classId) {
        // Tìm lớp học theo ID
        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new ClassNotFoundException("Class not found"));

        // Tìm department liên kết
        Department department = departmentRepository.findById(classEntity.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        // Trả về DTO cho lớp học và department
        return mapToClassResponseDTO(classEntity, department);
    }

    @Override
    public List<ClassResponseDTO> getAllClasses() {
        // Lấy tất cả các lớp học
        List<ClassEntity> classEntities = classRepository.findAll();
        return classEntities.stream().map(classEntity -> {
            Department department = departmentRepository.findById(classEntity.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
            return mapToClassResponseDTO(classEntity, department);
        }).collect(Collectors.toList());
    }

    @Override
    public ClassResponseDTO updateClass(int classId, ClassRequestDTO classRequestDTO) {
        // Kiểm tra nếu các trường trong request trống hoặc không hợp lệ
        if (!StringUtils.hasText(classRequestDTO.getClassName()) || classRequestDTO.getDepartmentId() == null) {
            throw new InvalidRequestException("Class name and Department ID must not be empty");
        }

        // Tìm lớp học theo ID
        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new ClassNotFoundException("Class not found"));

        // Tìm department từ departmentId
        Department department = departmentRepository.findById(classRequestDTO.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        // Cập nhật thông tin class
        classEntity.setClassName(classRequestDTO.getClassName());
        classEntity.setDepartmentId(classRequestDTO.getDepartmentId());

        // Lưu class đã cập nhật
        ClassEntity updatedClass = classRepository.save(classEntity);

        // Trả về response DTO cho class đã cập nhật và department
        return mapToClassResponseDTO(updatedClass, department);
    }

    @Override
    public void deleteClass(int classId) {
        // Tìm lớp học theo ID
        ClassEntity classEntity = classRepository.findById(classId)
                .orElseThrow(() -> new ClassNotFoundException("Class not found"));

        // Xóa class
        classRepository.delete(classEntity);
    }

    @Override
    public List<ClassResponseDTO> searchClassesByName(String className) {
        // Tìm kiếm các lớp học theo tên
        List<ClassEntity> classEntities = classRepository.findByClassNameContainingIgnoreCase(className);
        return classEntities.stream().map(classEntity -> {
            Department department = departmentRepository.findById(classEntity.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
            return mapToClassResponseDTO(classEntity, department);
        }).collect(Collectors.toList());
    }

    // Phương thức giúp chuẩn bị dữ liệu phản hồi (DTO)
    private ClassResponseDTO mapToClassResponseDTO(ClassEntity savedClass, Department department) {
        ClassResponseDTO responseDTO = new ClassResponseDTO();
        responseDTO.setClassId(savedClass.getClassId());
        responseDTO.setClassName(savedClass.getClassName());
        responseDTO.setDepartmentId(String.valueOf(savedClass.getDepartmentId()));

        DepartmentResponseDTO departmentDTO = new DepartmentResponseDTO();
        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());


        return responseDTO;
    }
}

