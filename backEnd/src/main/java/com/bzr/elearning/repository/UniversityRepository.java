package com.bzr.elearning.repository;

import com.bzr.elearning.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository< University, Long> {
}
