package org.aibles.quanlysinhvien.service.impl;


import org.aibles.quanlysinhvien.dto.LecturerRequestDTO;
import org.aibles.quanlysinhvien.dto.LecturerResponseDTO;
import org.aibles.quanlysinhvien.entity.Lecturer;
import org.aibles.quanlysinhvien.exception.exception.LecturerNotFoundException;
import org.aibles.quanlysinhvien.exception.exception.InvalidRequestException;
import org.aibles.quanlysinhvien.repository.LecturerRepository;
import org.aibles.quanlysinhvien.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public LecturerResponseDTO createLecturer(LecturerRequestDTO lecturerRequestDTO) {
        // Kiểm tra tính hợp lệ của request
        validateLecturerRequest(lecturerRequestDTO);

        Lecturer lecturer = new Lecturer();
        lecturer.setFullName(lecturerRequestDTO.getFullName());
        lecturer.setDateOfBirth(lecturerRequestDTO.getDateOfBirth());
        lecturer.setGender(Lecturer.Gender.valueOf(lecturerRequestDTO.getGender()));
        lecturer.setEmail(lecturerRequestDTO.getEmail());
        lecturer.setPhoneNumber(lecturerRequestDTO.getPhoneNumber());
        lecturer.setDepartmentId(lecturerRequestDTO.getDepartmentId());

        Lecturer savedLecturer = lecturerRepository.save(lecturer);

        return mapToLecturerResponseDTO(savedLecturer);
    }

    @Override
    public List<LecturerResponseDTO> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        return lecturers.stream().map(this::mapToLecturerResponseDTO).collect(Collectors.toList());
    }

    @Override
    public LecturerResponseDTO getLecturerById(int lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new LecturerNotFoundException("Lecturer with ID " + lecturerId + " not found."));
        return mapToLecturerResponseDTO(lecturer);
    }

    @Override
    public LecturerResponseDTO updateLecturer(int lecturerId, LecturerRequestDTO lecturerRequestDTO) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new LecturerNotFoundException("Lecturer with ID " + lecturerId + " not found."));

        // Kiểm tra tính hợp lệ của request
        validateLecturerRequest(lecturerRequestDTO);

        lecturer.setFullName(lecturerRequestDTO.getFullName());
        lecturer.setDateOfBirth(lecturerRequestDTO.getDateOfBirth());
        lecturer.setGender(Lecturer.Gender.valueOf(lecturerRequestDTO.getGender()));
        lecturer.setEmail(lecturerRequestDTO.getEmail());
        lecturer.setPhoneNumber(lecturerRequestDTO.getPhoneNumber());
        lecturer.setDepartmentId(lecturerRequestDTO.getDepartmentId());

        Lecturer updatedLecturer = lecturerRepository.save(lecturer);
        return mapToLecturerResponseDTO(updatedLecturer);
    }

    @Override
    public void deleteLecturer(int lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new LecturerNotFoundException("Lecturer with ID " + lecturerId + " not found."));
        lecturerRepository.delete(lecturer);
    }

    // Phương thức kiểm tra tính hợp lệ của LecturerRequestDTO
    private void validateLecturerRequest(LecturerRequestDTO lecturerRequestDTO) {
        if (!StringUtils.hasText(lecturerRequestDTO.getFullName())) {
            throw new InvalidRequestException("Full name must not be empty.");
        }
        if (!StringUtils.hasText(lecturerRequestDTO.getGender())) {
            throw new InvalidRequestException("Gender must not be empty.");
        }
        if (!StringUtils.hasText(lecturerRequestDTO.getEmail())) {
            throw new InvalidRequestException("Email must not be empty.");
        }
        if (!StringUtils.hasText(lecturerRequestDTO.getPhoneNumber())) {
            throw new InvalidRequestException("Phone number must not be empty.");
        }
        if (lecturerRequestDTO.getDepartmentId() == null) {
            throw new InvalidRequestException("Department ID must not be null.");
        }
    }

    private LecturerResponseDTO mapToLecturerResponseDTO(Lecturer lecturer) {
        LecturerResponseDTO lecturerResponseDTO = new LecturerResponseDTO();
        lecturerResponseDTO.setLecturerId(lecturer.getLecturerId());
        lecturerResponseDTO.setFullName(lecturer.getFullName());
        lecturerResponseDTO.setDateOfBirth(lecturer.getDateOfBirth());
        lecturerResponseDTO.setGender(lecturer.getGender().name());
        lecturerResponseDTO.setEmail(lecturer.getEmail());
        lecturerResponseDTO.setPhoneNumber(lecturer.getPhoneNumber());
        lecturerResponseDTO.setDepartmentId(lecturer.getDepartmentId());
        return lecturerResponseDTO;
    }
}

