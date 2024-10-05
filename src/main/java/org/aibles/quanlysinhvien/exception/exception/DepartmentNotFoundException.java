package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class DepartmentNotFoundException extends BaseException {

    public DepartmentNotFoundException(String message) {
        super(message);
        this.setCode("DEPARTMENT_NOT_FOUND");
    }
}

