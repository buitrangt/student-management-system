package org.aibles.quanlysinhvien.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClassRequestDTO {
    private String className;

    private Integer departmentId;


}

