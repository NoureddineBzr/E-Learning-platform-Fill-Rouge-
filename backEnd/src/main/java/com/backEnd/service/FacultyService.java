package com.backEnd.service;

import com.backEnd.dto.FacultyDto;

import java.util.List;

public interface FacultyService {


    List<FacultyDto> findAll();
    FacultyDto findById(Long id);
    FacultyDto save(FacultyDto dto);
    FacultyDto update(FacultyDto dto);
    void deleteById(Long id);



}
