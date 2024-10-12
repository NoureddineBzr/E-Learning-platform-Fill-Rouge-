package com.elearning.service;

import com.elearning.Dto.AcademicSpecializationDto;
import com.elearning.Dto.FacultyDto;

import java.util.List;

public interface AcademicSpecializationService {

    List<AcademicSpecializationDto> findAll();
    AcademicSpecializationDto save(AcademicSpecializationDto dto);
    void deleteById(Long id);
    List<AcademicSpecializationDto> findByName(String name);

}
