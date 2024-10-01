package com.backEnd.repository;

import com.backEnd.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByAuthorId(Long authorId);



}