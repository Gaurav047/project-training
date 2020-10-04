package com.sapient.customer.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.sapient.customer.beans.CreditCard;

class UtilityTest {

	@Test
	void isValidEmailPatternTest() {
		String input = "user@.com";
		boolean actual = Utility.isValidEmailPattern(input);
		assertEquals(false, actual);
		
		input = "u.com";
		actual = Utility.isValidEmailPattern(input);
		assertEquals(false, actual);
		
		input = "user@emailc.com";
		actual = Utility.isValidEmailPattern(input);
		assertEquals(true, actual);
	}
	
	@Test
	void hasASpecialCharTest() {
		
		String input = "abc@";
		boolean actual = Utility.hasASpecialChar(input);
		assertEquals(true, actual);
		
		input = "abc@#$#";
		actual = Utility.hasASpecialChar(input);
		assertEquals(true, actual);
		
		input = "abcdserfda";
		actual = Utility.hasASpecialChar(input);
		assertEquals(false, actual);
	}
	
	@Test
	void isAtleastEighteenTest() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
		Date dob = sdf.parse("1995-12-09");
		boolean actual = Utility.isAtleastEighteen(dob);
		assertEquals(true, actual);
		
		dob = sdf.parse("2021-21-01");
		actual = Utility.isAtleastEighteen(dob);
		assertEquals(false, actual);
	}
	
	@Test
	void isStringNonEmptyTest() {
		String input = "";
		boolean actual = Utility.isStringNonEmpty(input);
		assertEquals(false, actual);
		
		input = "asd";
		actual = Utility.isStringNonEmpty(input);
		assertEquals(true, actual);
	}
	
	@Test
	void isCreditCardNumberValidTest() {
		String cardNumber = "66011979268936225";
		boolean actual = Utility.isCreditCardNumberValid(cardNumber);
		assertEquals(true, actual);
		
		cardNumber = "6762805855268402";
		actual = Utility.isCreditCardNumberValid(cardNumber);
		assertEquals(true, actual);
		
		cardNumber = "676151854834271";
		actual = Utility.isCreditCardNumberValid(cardNumber);
		assertEquals(false, actual);
	}
	
	@Test
	void isCreditCardValidTest() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
		CreditCard card = new CreditCard("36360219198716", "291", sdf.parse("2020-29-12"), null);
		boolean actual = Utility.isCreditCardValid(card);
		assertEquals(true, actual);
		
		card = new CreditCard("36360219198726", "291", sdf.parse("2020-29-12"), null);
		actual = Utility.isCreditCardValid(card);
		assertEquals(false, actual);
		
		card = new CreditCard("36360219198716", "291", sdf.parse("2020-20-09"), null);
		actual = Utility.isCreditCardValid(card);
		assertEquals(false, actual);
		
	}

}
