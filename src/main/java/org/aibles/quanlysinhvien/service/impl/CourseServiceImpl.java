package org.aibles.quanlysinhvien.service.impl;


import org.aibles.quanlysinhvien.dto.CourseRequestDTO;
import org.aibles.quanlysinhvien.dto.CourseResponseDTO;
import org.aibles.quanlysinhvien.entity.Course;
import org.aibles.quanlysinhvien.exception.exception.*;
import org.aibles.quanlysinhvien.repository.CourseRepository;
import org.aibles.quanlysinhvien.repository.LecturerRepository;
import org.aibles.quanlysinhvien.repository.SubjectRepository;
import org.aibles.quanlysinhvien.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final LecturerRepository lecturerRepository;

    public CourseServiceImpl(CourseRepository courseRepository, SubjectRepository subjectRepository, LecturerRepository lecturerRepository) {
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        // Kiểm tra các trường hợp giá trị bị trống hoặc không hợp lệ
        validateCourseRequest(courseRequestDTO);

        // Kiểm tra subjectId có tồn tại không
        if (!subjectRepository.existsById(courseRequestDTO.getSubjectId())) {
            throw new SubjectNotFoundException("Subject with ID " + courseRequestDTO.getSubjectId() + " not found.");
        }

        // Kiểm tra lecturerId có tồn tại không
        if (!lecturerRepository.existsById(courseRequestDTO.getLecturerId())) {
            throw new LecturerNotFoundException("Lecturer with ID " + courseRequestDTO.getLecturerId() + " not found.");
        }

        Course course = new Course();
        course.setSubjectId(courseRequestDTO.getSubjectId());
        course.setLecturerId(courseRequestDTO.getLecturerId());
        course.setSemester(courseRequestDTO.getSemester());
        course.setAcademicYear(courseRequestDTO.getAcademicYear());

        Course savedCourse = courseRepository.save(course);

        return mapToCourseResponseDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        // Kiểm tra các trường hợp giá trị bị trống hoặc không hợp lệ
        validateCourseRequest(courseRequestDTO);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " not found."));

        // Kiểm tra subjectId có tồn tại không
        if (!subjectRepository.existsById(courseRequestDTO.getSubjectId())) {
            throw new SubjectNotFoundException("Subject with ID " + courseRequestDTO.getSubjectId() + " not found.");
        }

        // Kiểm tra lecturerId có tồn tại không
        if (!lecturerRepository.existsById(courseRequestDTO.getLecturerId())) {
            throw new LecturerNotFoundException("Lecturer with ID " + courseRequestDTO.getLecturerId() + " not found.");
        }

        course.setSubjectId(courseRequestDTO.getSubjectId());
        course.setLecturerId(courseRequestDTO.getLecturerId());
        course.setSemester(courseRequestDTO.getSemester());
        course.setAcademicYear(courseRequestDTO.getAcademicYear());

        Course updatedCourse = courseRepository.save(course);
        return mapToCourseResponseDTO(updatedCourse);
    }

    private void validateCourseRequest(CourseRequestDTO courseRequestDTO) {
        // Kiểm tra xem các trường có bị bỏ trống hay không
        if (courseRequestDTO.getSubjectId() == null) {
            throw new InvalidRequestException("Subject ID must not be null.");
        }
        if (courseRequestDTO.getLecturerId() == null) {
            throw new InvalidRequestException("Lecturer ID must not be null.");
        }
        if (!StringUtils.hasText(courseRequestDTO.getSemester())) {
            throw new InvalidRequestException("Semester must not be empty.");
        }
        if (!StringUtils.hasText(courseRequestDTO.getAcademicYear())) {
            throw new InvalidRequestException("Academic year must not be empty.");
        }
    }

    private CourseResponseDTO mapToCourseResponseDTO(Course course) {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseId(course.getCourseId());
        courseResponseDTO.setSubjectId(course.getSubjectId());
        courseResponseDTO.setLecturerId(course.getLecturerId());
        courseResponseDTO.setSemester(course.getSemester());
        courseResponseDTO.setAcademicYear(course.getAcademicYear());
        return courseResponseDTO;
    }

    @Override
    public void deleteCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " not found."));
        courseRepository.delete(course);
    }

    @Override
    public CourseResponseDTO getCourseById(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " not found."));
        return mapToCourseResponseDTO(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToCourseResponseDTO).collect(Collectors.toList());
    }
}

