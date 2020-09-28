package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserApplicationTests {
	
	@Autowired
	UserRepository userMapper;
	
	
	@Test
	public void login() throws Exception {
        String myPassword = "4321";
        String changePassword = "$2a$10$wHwGLfY9.GAetCJM7252i.l4kllJKdYs7wLJEy5vQ481B5VBbAAVS";
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String change = encoder.encode(myPassword);
        
        boolean pass = encoder.matches(myPassword, changePassword);
        
        System.out.println("match ::"+pass);
	}

}
