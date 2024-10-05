package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class ClassNotFoundException extends BaseException {

    public ClassNotFoundException(String message) {
        super(message);
        this.setCode("class_not_found"); // Mã lỗi tùy chỉnh
    }
}

