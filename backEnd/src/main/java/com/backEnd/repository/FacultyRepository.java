package com.backEnd.repository;

import com.backEnd.entity.AcademicSpecialization;
import com.backEnd.entity.Faculty;
import com.backEnd.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long > {

    List<Faculty> findByAcademicSpecializationAndUniversity(AcademicSpecialization specialization, University university);
}
