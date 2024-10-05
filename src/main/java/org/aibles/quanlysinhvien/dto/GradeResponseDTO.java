package org.aibles.quanlysinhvien.dto;


import lombok.Data;

@Data
public class GradeResponseDTO {
    private Integer studentId;
    private Integer courseId;
    private Float score;
}

