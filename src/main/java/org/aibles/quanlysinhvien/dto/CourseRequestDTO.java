package org.aibles.quanlysinhvien.dto;


import lombok.Data;

@Data
public class CourseRequestDTO {

    private Integer subjectId;
    private Integer lecturerId;
    private String semester;
    private String academicYear;
}

