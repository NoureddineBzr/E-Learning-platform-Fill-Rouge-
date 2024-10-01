package com.backEnd.service;

import com.backEnd.dto.AcademicSpecializationDto;

import java.util.List;

public interface AcademicSpecializationService {

    List<AcademicSpecializationDto> findAll();
    AcademicSpecializationDto save(AcademicSpecializationDto dto);
    void deleteById(Long id);
    List<AcademicSpecializationDto> findByName(String name);

}