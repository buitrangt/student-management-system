package org.aibles.quanlysinhvien.entity;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "student_course")
@IdClass(StudentCourseId.class)

public class StudentCourse {

    @Id
    @Column(name = "student_id")
    private Integer studentId;  // Sử dụng student_id thay vì quan hệ trực tiếp

    @Id
    @Column(name = "course_id")
    private Integer courseId;  // Sử dụng course_id thay vì quan hệ trực tiếp
}

