package com.elearning.repository;

import com.elearning.Dto.CourseDto;
import com.elearning.entity.Course;
import com.elearning.entity.Role;
import com.elearning.entity.StudentsEnrolledCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StudentsEnrolledCourcesRepository extends JpaRepository<StudentsEnrolledCourse, Long> {

    int countByCourseId(Long id);

    Set<StudentsEnrolledCourse> findByStudentId(Long studentId);
}