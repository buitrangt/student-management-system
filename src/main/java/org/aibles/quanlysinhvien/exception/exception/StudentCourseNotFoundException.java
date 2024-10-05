package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class StudentCourseNotFoundException extends BaseException {

    public StudentCourseNotFoundException(String message) {
        super(message);
        this.setCode("student_course_not_found");
    }
}

