package org.aibles.quanlysinhvien.exception.exception;


import org.aibles.quanlysinhvien.exception.BaseException;

public class InvalidRequestException extends BaseException {

    public InvalidRequestException(String message) {
        super(message);
        this.setCode("invalid_request"); // Mã lỗi tùy chỉnh
    }
}
