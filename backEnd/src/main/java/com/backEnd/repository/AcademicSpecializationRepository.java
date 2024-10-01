package com.backEnd.repository;

import com.backEnd.entity.AcademicSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicSpecializationRepository extends JpaRepository<AcademicSpecialization,Long> {
    List<AcademicSpecialization> findByName(String name);
}