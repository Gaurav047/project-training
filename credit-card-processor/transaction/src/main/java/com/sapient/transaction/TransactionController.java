package com.sapient.transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

	@GetMapping
	public String home()
	{
		return "Hi Transaction";
	}
}
