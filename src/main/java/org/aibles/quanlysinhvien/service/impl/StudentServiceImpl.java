package org.aibles.quanlysinhvien.service.impl;


import org.aibles.quanlysinhvien.dto.StudentRequestDTO;
import org.aibles.quanlysinhvien.dto.StudentResponseDTO;
import org.aibles.quanlysinhvien.entity.Student;
import org.aibles.quanlysinhvien.exception.exception.StudentNotFoundException;
import org.aibles.quanlysinhvien.repository.StudentRepository;
import org.aibles.quanlysinhvien.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setFullName(studentRequestDTO.getFullName());
        student.setDateOfBirth(studentRequestDTO.getDateOfBirth());
        student.setGender(Student.Gender.valueOf(studentRequestDTO.getGender()));
        student.setAddress(studentRequestDTO.getAddress());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        student.setClassId(studentRequestDTO.getClassId());

        Student savedStudent = studentRepository.save(student);

        return mapToStudentResponseDTO(savedStudent);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToStudentResponseDTO).collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found."));
        return mapToStudentResponseDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(int studentId, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found."));

        student.setFullName(studentRequestDTO.getFullName());
        student.setDateOfBirth(studentRequestDTO.getDateOfBirth());
        student.setGender(Student.Gender.valueOf(studentRequestDTO.getGender()));
        student.setAddress(studentRequestDTO.getAddress());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPhoneNumber(studentRequestDTO.getPhoneNumber());
        student.setClassId(studentRequestDTO.getClassId());

        Student updatedStudent = studentRepository.save(student);
        return mapToStudentResponseDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found."));
        studentRepository.delete(student);
    }

    private StudentResponseDTO mapToStudentResponseDTO(Student student) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setStudentId(student.getStudentId());
        studentResponseDTO.setFullName(student.getFullName());
        studentResponseDTO.setDateOfBirth(student.getDateOfBirth());
        studentResponseDTO.setGender(student.getGender().name());
        studentResponseDTO.setAddress(student.getAddress());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setPhoneNumber(student.getPhoneNumber());
        studentResponseDTO.setClassId(student.getClassId());
        return studentResponseDTO;
    }
}

