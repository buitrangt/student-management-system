package org.aibles.quanlysinhvien.dto;


import lombok.Data;

@Data
public class GradeRequestDTO {
    private Integer studentId;
    private Integer courseId;
    private Float score;
}

