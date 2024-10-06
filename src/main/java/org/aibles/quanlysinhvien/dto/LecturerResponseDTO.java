package org.aibles.quanlysinhvien.dto;


import lombok.Data;


import lombok.Data;

import java.util.Date;

@Data
public class LecturerResponseDTO {
    private Integer lecturerId;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String email;
    private String phoneNumber;
    private Integer departmentId;
}
