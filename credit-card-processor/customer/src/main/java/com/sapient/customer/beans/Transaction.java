package com.sapient.customer.beans;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

public class Transaction {
	@Id
	private String transactionId;
	private String ccNumber;
	private Double transactionAmount;
	private String transactionType;
	private String transactionMode;
	private LocalDate transactionDate;
	private LocalTime transactionTime;
	private String transactionLocation;
	private String transactionIP;
	private String transactionStatus;
	private String merchantId;
	private String transactionDescription;

	public Transaction() {
		// TODO Auto-generated constructor stub
	
	}

	public Transaction(String transactionId, String ccNumber, Double transactionAmount, String transactionType,
			String transactionMode, LocalDate transactionDate, LocalTime transactionTime, String transactionLocation,
			String transactionIP, String transactionStatus, String merchantId, String transactionDescription) {
		super();
		this.transactionId = transactionId;
		this.ccNumber = ccNumber;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.transactionMode = transactionMode;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.transactionLocation = transactionLocation;
		this.transactionIP = transactionIP;
		this.transactionStatus = transactionStatus;
		this.merchantId = merchantId;
		this.transactionDescription = transactionDescription;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public LocalTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getTransactionLocation() {
		return transactionLocation;
	}

	public void setTransactionLocation(String transactionLocation) {
		this.transactionLocation = transactionLocation;
	}

	public String getTransactionIP() {
		return transactionIP;
	}

	public void setTransactionIP(String transactionIP) {
		this.transactionIP = transactionIP;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", ccNumber=" + ccNumber + ", transactionAmount="
				+ transactionAmount + ", transactionType=" + transactionType + ", transactionMode=" + transactionMode
				+ ", transactionDate=" + transactionDate + ", transactionTime=" + transactionTime
				+ ", transactionLocation=" + transactionLocation + ", transactionIP=" + transactionIP
				+ ", transactionStatus=" + transactionStatus + ", merchantId=" + merchantId
				+ ", transactionDescription=" + transactionDescription + "]";
	}

	
}
