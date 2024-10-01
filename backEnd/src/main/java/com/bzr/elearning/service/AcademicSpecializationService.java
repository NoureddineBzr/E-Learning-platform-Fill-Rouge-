package com.bzr.elearning.service;

import com.bzr.elearning.Dto.AcademicSpecializationDto;
import com.bzr.elearning.Dto.FacultyDto;

import java.util.List;

public interface AcademicSpecializationService {

    List<AcademicSpecializationDto> findAll();
    AcademicSpecializationDto save(AcademicSpecializationDto dto);
    void deleteById(Long id);
    List<AcademicSpecializationDto> findByName(String name);

}
