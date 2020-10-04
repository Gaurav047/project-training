package com.sapient.customer.payload.response;

import com.sapient.customer.beans.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Customer customer;
	private MessageResponse message;
	
	public JwtResponse(String token, Customer customer, MessageResponse messageResponse) {
		super();
		this.token = token;
		this.customer = customer;
		this.message = messageResponse;
	}
}
