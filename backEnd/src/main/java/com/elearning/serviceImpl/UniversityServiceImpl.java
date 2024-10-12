package com.elearning.serviceImpl;

import com.elearning.Dto.UniversityDto;
import com.elearning.entity.University;
import com.elearning.exception.RecordNotFoundException;
import com.elearning.mapping.UniversityMapper;
import com.elearning.repository.UniversityRepository;
import com.elearning.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    @Override
    public List<UniversityDto> findAll() {
        List<UniversityDto> universities = new ArrayList<>();
        universityRepository.findAll().forEach(e-> universities.add(UniversityMapper.entityToDto(e)));
        return universities;
    }

    @Override
    public UniversityDto save(UniversityDto dto) {
      return UniversityMapper.entityToDto(universityRepository.save(UniversityMapper.dtoToEntity(dto)));
    }

    @Override
    public void deleteById(Long id) {

        Optional<University> university = universityRepository.findById(id);

        if(university.isPresent()){
            universityRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No university With Id: "+ id);
        }
    }


}
