package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class LecturerNotFoundException extends BaseException {

    public LecturerNotFoundException(String message) {
        super(message);
        this.setCode("lecturer_not_found");
    }
}

