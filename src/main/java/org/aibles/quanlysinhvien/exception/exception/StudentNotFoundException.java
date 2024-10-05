package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class StudentNotFoundException extends BaseException {

    public StudentNotFoundException(String message) {
        super(message);
        this.setCode("student_not_found");
    }
}

