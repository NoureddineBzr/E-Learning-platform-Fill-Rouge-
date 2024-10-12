package com.elearning.repository;

import com.elearning.entity.AcademicSpecialization;
import com.elearning.entity.Faculty;
import com.elearning.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long > {

    List<Faculty> findByAcademicSpecializationAndUniversity(AcademicSpecialization specialization, University university);
}
