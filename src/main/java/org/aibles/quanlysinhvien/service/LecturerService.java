package org.aibles.quanlysinhvien.service;


import org.aibles.quanlysinhvien.dto.LecturerRequestDTO;
import org.aibles.quanlysinhvien.dto.LecturerResponseDTO;

import java.util.List;

public interface LecturerService {

    LecturerResponseDTO createLecturer(LecturerRequestDTO lecturerRequestDTO);

    List<LecturerResponseDTO> getAllLecturers();

    LecturerResponseDTO getLecturerById(int lecturerId);

    LecturerResponseDTO updateLecturer(int lecturerId, LecturerRequestDTO lecturerRequestDTO);

    void deleteLecturer(int lecturerId);
}

