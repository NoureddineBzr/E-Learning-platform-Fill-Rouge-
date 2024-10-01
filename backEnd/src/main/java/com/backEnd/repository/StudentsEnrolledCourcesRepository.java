package com.backEnd.repository;

import com.backEnd.entity.StudentsEnrolledCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentsEnrolledCourcesRepository extends JpaRepository<StudentsEnrolledCourse, Long> {

    int countByCourseId(Long id);

    Set<StudentsEnrolledCourse> findByStudentId(Long studentId);
}
