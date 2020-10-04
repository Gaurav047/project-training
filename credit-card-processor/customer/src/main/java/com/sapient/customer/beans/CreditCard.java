package com.sapient.customer.beans;

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
public class CreditCard {
	
	private String creditCardNo;
	private String cvv;
	private Date expiry;
	private Boolean status;

}
