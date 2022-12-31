package com.StudentCourse.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.StudentCourse.repository.UserRepo;
import com.StudentCourse.schema.Student;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new MyUserDetails(username);
        Optional<Student> user =  userRepo.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found"));
        return user.map(MyUserDetails::new).get();
    }
}
