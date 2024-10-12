package com.elearning.repository;

import com.elearning.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository< University, Long> {
}
