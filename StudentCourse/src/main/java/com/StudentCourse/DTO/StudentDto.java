package com.StudentCourse.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String fullName;
    private String username;
    private String password;
    private LocalDateTime dateOfBirth;
}
