package com.elearning.serviceImpl;

import com.elearning.Dto.FacultyDto;
import com.elearning.entity.Faculty;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.exception.RecordNotFoundException;
import com.elearning.mapping.AcademicSpecializationMapper;
import com.elearning.mapping.FacultyMapper;
import com.elearning.mapping.UniversityMapper;
import com.elearning.repository.AcademicSpecializationRepository;
import com.elearning.repository.FacultyRepository;
import com.elearning.repository.UniversityRepository;
import com.elearning.service.FacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {


    private final FacultyRepository facultyRepository;
    private final AcademicSpecializationRepository specializationRepository;
    private final UniversityRepository universityRepository;


    @Override
    public List<FacultyDto> findAll() {
        List<FacultyDto> faculties = new ArrayList<>();
        facultyRepository.findAll().forEach(e-> faculties.add(FacultyMapper.entityToDto(e)));
        return faculties;
    }

    @Override
    public FacultyDto save(FacultyDto dto) {
        List<Faculty> faculties = facultyRepository.findByAcademicSpecializationAndUniversity(
                AcademicSpecializationMapper.dtoToEntity(dto.getSepicailization()),
                UniversityMapper.dtoToEntity(dto.getUniversity())
        );
        if(!faculties.isEmpty()){
            throw new DuplicateRecordException("Faculty already found");
        }
        Faculty faculty = facultyRepository.save(FacultyMapper.dtoToEntity(dto));
       return findById(faculty.getId());
    }

    @Override
    public void deleteById(Long id) {

        Optional<Faculty> faculty = facultyRepository.findById(id);

        if(faculty.isPresent()){
            facultyRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No Faculty With Id: "+ id);
        }
    }

@Override
    public FacultyDto findById(Long id){
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if(faculty.isPresent()){
           return  FacultyMapper.entityToDto(faculty.get());
        }else{
            throw new RecordNotFoundException("No Faculty With Id: "+ id);
        }
    }

    @Override
    public FacultyDto update(FacultyDto dto){

        Faculty faculty = new Faculty();

        faculty.setUniversity(UniversityMapper.dtoToEntity(dto.getUniversity()));
        faculty.setAcademicSpecialization(AcademicSpecializationMapper.dtoToEntity(dto.getSepicailization()));

        return FacultyMapper.entityToDto(facultyRepository.save(faculty)) ;
    }
}
