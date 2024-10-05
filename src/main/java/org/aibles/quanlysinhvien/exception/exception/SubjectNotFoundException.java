package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class SubjectNotFoundException extends BaseException {

    public SubjectNotFoundException(String message) {
        super(message);
        this.setCode("subject_not_found");
    }
}

