package com.sapient.customer.beans;

import java.util.List;

public class CustomPOJO_A {

	private String ccNumber;
	private List<CustomPOJO_B> transactions;

	public CustomPOJO_A() {
		// TODO Auto-generated constructor stub
	}

	public CustomPOJO_A(String ccNumber, List<CustomPOJO_B> transactions) {
		super();
		this.ccNumber = ccNumber;
		this.transactions = transactions;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public List<CustomPOJO_B> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<CustomPOJO_B> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "CustomPOJO_A [ccNumber=" + ccNumber + ", transactions=" + transactions + "]";
	}
	
}
