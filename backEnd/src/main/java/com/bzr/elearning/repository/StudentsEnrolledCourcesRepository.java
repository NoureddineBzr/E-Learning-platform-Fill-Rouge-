package com.bzr.elearning.repository;

import com.bzr.elearning.Dto.CourseDto;
import com.bzr.elearning.entity.Course;
import com.bzr.elearning.entity.Role;
import com.bzr.elearning.entity.StudentsEnrolledCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StudentsEnrolledCourcesRepository extends JpaRepository<StudentsEnrolledCourse, Long> {

    int countByCourseId(Long id);

    Set<StudentsEnrolledCourse> findByStudentId(Long studentId);
}