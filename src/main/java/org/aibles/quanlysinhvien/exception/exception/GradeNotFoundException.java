package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class GradeNotFoundException extends BaseException {

    public GradeNotFoundException(String message) {
        super(message);
        this.setCode("grade_not_found");
    }
}

