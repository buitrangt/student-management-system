package org.aibles.quanlysinhvien.service;


import org.aibles.quanlysinhvien.dto.GradeRequestDTO;
import org.aibles.quanlysinhvien.dto.GradeResponseDTO;

import java.util.List;

public interface GradeService {

    GradeResponseDTO createGrade(GradeRequestDTO gradeRequestDTO);

    List<GradeResponseDTO> getAllGrades();

    GradeResponseDTO getGradeById(Integer studentId, Integer courseId);

    GradeResponseDTO updateGrade(Integer studentId, Integer courseId, GradeRequestDTO gradeRequestDTO);

    void deleteGrade(Integer studentId, Integer courseId);
}

