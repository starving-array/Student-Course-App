package com.StudentCourse.schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fullName;
	private String username;
	private String password;
	private boolean active;
	private String roles;
	private LocalDateTime dateOfBirth;

	@JsonIgnore
	@ManyToMany
	@JoinColumn(name = "Course_student", referencedColumnName = "id")
	List<Course> courses = new ArrayList<>();

}
