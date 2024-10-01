package com.bzr.elearning.service;

import com.bzr.elearning.Dto.FacultyDto;
import com.bzr.elearning.entity.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {


    List<FacultyDto> findAll();
    FacultyDto findById(Long id);
    FacultyDto save(FacultyDto dto);
    FacultyDto update(FacultyDto dto);
    void deleteById(Long id);



}
