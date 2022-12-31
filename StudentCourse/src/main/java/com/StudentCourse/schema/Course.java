package com.StudentCourse.schema;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String coursename;
	
	@Column(unique = true)
	private String coursecode;

	@JsonIgnore
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	List<Student> students = new ArrayList<>();

}
