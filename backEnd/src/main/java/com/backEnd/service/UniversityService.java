package com.backEnd.service;


import com.backEnd.dto.UniversityDto;

import java.util.List;

public interface UniversityService {


    List<UniversityDto> findAll();
    UniversityDto save(UniversityDto dto);
    void deleteById(Long id);


}