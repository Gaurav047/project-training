package com.sapient.customer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.payload.request.LoginRequest;
import com.sapient.customer.payload.response.MessageResponse;
import com.sapient.customer.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@Autowired
	ObjectMapper mapper;

	@PostMapping(value = "/customer/login", produces = { "application/hal+json" })
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//		
//		System.out.println(roles.toString()); // Customer Role is Coming
		try {
			return authService.loginCustomer(loginRequest);
		} catch (BadCredentialsException e) {
			return null;
		}

	}

	@PostMapping("/customer/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody Customer customer) {

		try {
			String response = authService.registerCustomer(customer);
			String[] errors = response.contains("\n") ? response.split("\n") : new String[] {};

			ObjectNode objectNode = mapper.createObjectNode();
			String errorMessage = "Please validate the following data ";
			objectNode.put("errorMessage", errorMessage);
			ArrayNode allErrors = mapper.valueToTree(errors);
			objectNode.putArray("errors").addAll(allErrors);
			if (errors.length == 0) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponse<String>(response));
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponse<ObjectNode>(objectNode));
			}

		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("User with this email already exists");
		}
	}

	@PostMapping("/account")
	public String getAccount() {
		return "something";
	}

	@GetMapping("/creditcards")
	public String getCardsFromDB(Customer customer) {
		return "Credit Cards";
	}
}
