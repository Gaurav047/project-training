package com.sapient.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@GetMapping("/")
	public String home() {
		return "Hi Admin";
	}
}
