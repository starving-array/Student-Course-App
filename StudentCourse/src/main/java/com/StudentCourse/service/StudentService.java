package com.StudentCourse.service;

import java.util.List;

import com.StudentCourse.DTO.CourseDTO;
import com.StudentCourse.DTO.StudentDto;
import com.StudentCourse.exceptions.OtherException;
import com.StudentCourse.exceptions.studentexception;
import com.StudentCourse.schema.Student;

public interface StudentService {

	public Student addStudent(StudentDto student) throws studentexception;

	public CourseDTO addCourse(String coursename, String coursecode) throws OtherException;

	public String addCourseToStudent(Integer studentid, Integer courseid) throws studentexception, OtherException;

	public Student updateStudentDetails(StudentDto studentDto) throws studentexception;

	public List<Student> getStudentListByCourse(String coursecode) throws OtherException;

	public List<CourseDTO> getCourseDetailOfStudent(Integer studentid) throws studentexception;

	public List<CourseDTO> getAllCourse() throws OtherException;
}
