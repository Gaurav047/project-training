package com.sapient.customer.beans;

public class CustomPOJO_B {
	private String month;
	private Double amount;
	private String Description;

	public CustomPOJO_B() {
		// TODO Auto-generated constructor stub
	}

	public CustomPOJO_B(String month, Double amount, String description) {
		super();
		this.month = month;
		this.amount = amount;
		Description = description;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "CustomPOJO_B [month=" + month + ", amount=" + amount + ", Description=" + Description + "]";
	}

}
