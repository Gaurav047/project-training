package com.sapient.customer.beans;

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
public class Address {
	public int houseNo;
	public String streetName;
	public String city;
	public String state;
	public String country;
	public int zipCode;
}
