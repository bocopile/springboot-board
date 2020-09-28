package com.example.demo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

	@GetMapping("/login")
	public String index() {
		
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
	
}
