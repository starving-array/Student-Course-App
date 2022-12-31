package com.StudentCourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentCourse.schema.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

	public Course findByCoursecode(String coursecode);
}
