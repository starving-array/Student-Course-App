package com.StudentCourse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentCourse.DTO.CourseDTO;
import com.StudentCourse.DTO.StudentDto;
import com.StudentCourse.exceptions.OtherException;
import com.StudentCourse.exceptions.studentexception;
import com.StudentCourse.repository.CourseRepo;
import com.StudentCourse.repository.UserRepo;
import com.StudentCourse.schema.Course;
import com.StudentCourse.schema.Student;

@Service
public class StudentServImpl implements StudentService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Override
	public Student addStudent(StudentDto student) throws studentexception {
		Student studentnewStudent = new Student();
		studentnewStudent.setActive(true);
		studentnewStudent.setFullName(student.getFullName());
		studentnewStudent.setUsername(student.getUsername());
		studentnewStudent.setPassword(student.getPassword());
		studentnewStudent.setDateOfBirth(student.getDateOfBirth());
		studentnewStudent.setRoles("USER");
		userRepo.save(studentnewStudent);
		return studentnewStudent;
	}

	@Override
	public String addCourseToStudent(Integer studentid, Integer courseid) throws studentexception, OtherException {
		Optional<Course> opC = courseRepo.findById(courseid);
		if (opC == null) {
			throw new OtherException("No course found");
		}

		Optional<Student> opS = userRepo.findById(studentid);
		if (opS == null) {
			throw new studentexception("No student found");
		}

		Course course = opC.get();
		Student student = opS.get();

		student.getCourses().add(course);
		course.getStudents().add(student);
		userRepo.save(student);
	
		String msgString = "Course added successfully";
		return msgString;
	}

	@Override
	public CourseDTO addCourse(String coursename, String coursecode) throws OtherException {
		Course course = new Course();
		course.setCoursecode(coursecode);
		course.setCoursename(coursename);
		
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseCode(coursecode);
		courseDTO.setCourseName(coursename);
		courseRepo.save(course);
		return courseDTO;
	}

	@Override
	public Student updateStudentDetails(StudentDto studentDto) throws studentexception {
		Optional<Student> opS = userRepo.findByUsername(studentDto.getUsername());
		if (opS == null) {
			throw new studentexception("No student found");
		}
		Student student = opS.get();
		if (studentDto.getFullName() != null) {
			student.setFullName(studentDto.getFullName());
		}
		if (studentDto.getPassword() != null) {
			student.setPassword(studentDto.getPassword());
		}

		if (studentDto.getDateOfBirth() != null) {
			student.setDateOfBirth(studentDto.getDateOfBirth());
		}

		return userRepo.save(student);
	}

	@Override
	public List<Student> getStudentListByCourse(String coursecode) throws OtherException {
		Course course = courseRepo.findByCoursecode(coursecode);
		if (course == null) {
			throw new OtherException("No course found");
		}

		return course.getStudents();
	}

	@Override
	public List<CourseDTO> getCourseDetailOfStudent(Integer studentid) throws studentexception {
		Optional<Student> opS = userRepo.findById(studentid);
		if (opS == null) {
			throw new studentexception("No student found");
		}
		List<Course> courses =  opS.get().getCourses();
		List<CourseDTO> courseDTOs = new ArrayList<>();
		for(Course c:courses) {
			CourseDTO courseDTO = new CourseDTO();
			courseDTO.setCourseCode(c.getCoursecode());
			courseDTO.setCourseName(c.getCoursename());
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

	@Override
	public List<CourseDTO> getAllCourse() throws OtherException {
		List<Course> courses = courseRepo.findAll();
		if(courses.size()==0) {
			throw new OtherException("No course found");
		}
		
		List<CourseDTO> courseDTOs = new ArrayList<>();
		for(Course c:courses) {
			CourseDTO courseDTO = new CourseDTO();
			courseDTO.setCourseCode(c.getCoursecode());
			courseDTO.setCourseName(c.getCoursename());
			courseDTOs.add(courseDTO);
		}
		return courseDTOs;
	}

}
