package com.bzr.elearning.service;

import com.bzr.elearning.Dto.UniversityDto;

import java.util.List;

public interface UniversityService {


    List<UniversityDto> findAll();
    UniversityDto save(UniversityDto dto);
    void deleteById(Long id);


}
