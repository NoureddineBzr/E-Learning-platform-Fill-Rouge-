package com.bzr.elearning.repository;

import com.bzr.elearning.entity.AcademicSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AcademicSpecializationRepository extends JpaRepository<AcademicSpecialization,Long> {
    List<AcademicSpecialization> findByName(String name);
}
