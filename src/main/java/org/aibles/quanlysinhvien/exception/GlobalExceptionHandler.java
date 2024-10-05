package org.aibles.quanlysinhvien.exception;

import lombok.extern.slf4j.Slf4j;

import org.aibles.quanlysinhvien.constant.ResponseCode;
import org.aibles.quanlysinhvien.exception.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.ClassNotFoundException;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleDepartmentNotFoundException(DepartmentNotFoundException exception) {
        log.error("Error occurred: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(), // Sử dụng ResponseCode.NOT_FOUND
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse<String>> handleInvalidRequestException(InvalidRequestException exception) {
        log.error("Invalid request: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.INVALID_REQUEST.getValue(), // Sử dụng ResponseCode.INVALID_REQUEST
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleClassNotFoundException(ClassNotFoundException exception) {
        log.error("Class not found: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(),
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleCourseNotFoundException(CourseNotFoundException exception) {
        log.error("Course not found: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(),
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleSubjectNotFoundException(SubjectNotFoundException exception) {
        log.error("Subject not found: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(),
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LecturerNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleLecturerNotFoundException(LecturerNotFoundException exception) {
        log.error("Lecturer not found: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(),
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleGradeNotFoundException(GradeNotFoundException exception) {
        log.error("Grade not found: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(),
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleStudentNotFoundException(StudentNotFoundException exception) {
        log.error("Student not found: {}", exception.getMessage());
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(),
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentCourseNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleStudentCourseNotFoundException(StudentCourseNotFoundException exception) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                ResponseCode.NOT_FOUND.getValue(),
                System.currentTimeMillis(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }



}

