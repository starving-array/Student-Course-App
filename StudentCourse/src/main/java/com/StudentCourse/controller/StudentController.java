package com.StudentCourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.StudentCourse.DTO.CourseDTO;
import com.StudentCourse.DTO.StudentDto;
import com.StudentCourse.exceptions.OtherException;
import com.StudentCourse.exceptions.studentexception;
import com.StudentCourse.schema.Course;
import com.StudentCourse.schema.Student;
import com.StudentCourse.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome to Student Database</h1>");
	}
	
	@GetMapping("/user")
	public String userDetail() {
		return ("<h1>Welcome to Student Database</h1>");
	}

	@PostMapping("/newstudent")
	public ResponseEntity<Student> addStudent(@RequestBody StudentDto studentDto) throws studentexception {
		Student student = service.addStudent(studentDto);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@PostMapping("/courseasign/{sid}/{cid}")
	public ResponseEntity<String> assignCourseToStudent(@PathVariable("sid") Integer studentid,
			@PathVariable("cid") Integer courseid) throws studentexception, OtherException {
		String course = service.addCourseToStudent(studentid, courseid);
		return new ResponseEntity<String>(course, HttpStatus.OK);
	}

	@PostMapping("/newcourse/{cname}/{code}")
	public ResponseEntity<CourseDTO> addCourse(@PathVariable("cname") String coursename,
			@PathVariable("code") String coursecode) throws studentexception, OtherException {
		CourseDTO course = service.addCourse(coursename, coursecode);
		return new ResponseEntity<CourseDTO>(course, HttpStatus.OK);
	}

	@PatchMapping("/update")
	public ResponseEntity<Student> updateStudent(@RequestBody StudentDto studentDto) throws studentexception {
		Student student = service.updateStudentDetails(studentDto);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@GetMapping("/user/studentlist/{code}")
	public ResponseEntity<List<Student>> getStudentListByCourse(@PathVariable("code") String coursecode)
			throws OtherException {
		List<Student> list = service.getStudentListByCourse(coursecode);
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	@GetMapping("/user/courselist/{sid}")
	public ResponseEntity<List<CourseDTO>> getCourseDetailbyStudent(@PathVariable("sid") Integer studentid)
			throws OtherException, studentexception {
		List<CourseDTO> list = service.getCourseDetailOfStudent(studentid);
		return new ResponseEntity<List<CourseDTO>>(list, HttpStatus.OK);
	}

	@GetMapping("/course")
	public ResponseEntity<List<CourseDTO>> getCourse() throws OtherException {
		List<CourseDTO> courses = service.getAllCourse();
		return new ResponseEntity<List<CourseDTO>>(courses, HttpStatus.OK);
	}
}
