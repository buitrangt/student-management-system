package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class CourseNotFoundException extends BaseException {

    public CourseNotFoundException(String message) {
        super(message);
        this.setCode("course_not_found");
    }
}

