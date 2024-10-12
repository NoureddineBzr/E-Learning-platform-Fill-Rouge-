package com.elearning.serviceImpl;

import com.elearning.Dto.FacultyDto;
import com.elearning.entity.Faculty;
import com.elearning.exception.DuplicateRecordException;
import com.elearning.exception.RecordNotFoundException;
import com.elearning.mapping.FacultyMapper;
import com.elearning.repository.FacultyRepository;
import com.elearning.repository.AcademicSpecializationRepository;
import com.elearning.repository.UniversityRepository;
import com.elearning.serviceImpl.FacultyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @Mock
    private FacultyRepository facultyRepository;

    @Mock
    private AcademicSpecializationRepository specializationRepository;

    @Mock
    private UniversityRepository universityRepository;

    @InjectMocks
    private FacultyServiceImpl facultyService;

    private FacultyDto facultyDto;
    private Faculty faculty;

    @BeforeEach
    void setUp() {
        facultyDto = FacultyDto.builder()
                .id(1L)
                .sepicailization(null) // Ajoutez les données de DTO nécessaires
                .university(null)
                .build();

        faculty = Faculty.builder()
                .id(1L)
                .academicSpecialization(null)
                .university(null)
                .build();
    }







    @Test
    void findById_shouldThrowRecordNotFoundException_whenFacultyNotFound() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> facultyService.findById(1L));
    }

    @Test
    void deleteById_shouldDeleteFaculty() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        facultyService.deleteById(1L);

        verify(facultyRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteById_shouldThrowRecordNotFoundException_whenFacultyNotFound() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> facultyService.deleteById(1L));
        verify(facultyRepository, times(0)).deleteById(1L);
    }


}
