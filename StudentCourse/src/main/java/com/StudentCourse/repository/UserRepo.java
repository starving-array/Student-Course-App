package com.StudentCourse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentCourse.schema.Student;

public interface UserRepo extends JpaRepository<Student, Integer> {
    Optional<Student> findByUsername(String string);
}
