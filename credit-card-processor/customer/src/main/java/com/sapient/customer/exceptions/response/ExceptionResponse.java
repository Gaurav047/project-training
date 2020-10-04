package com.sapient.customer.exceptions.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {
	
	private String errorMessage;
	private String details;
	private String error;

}
