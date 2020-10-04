package com.sapient.customer.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sapient.customer.beans.CreditCard;

public class Utility {

	public static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final Pattern SPECIAL_CHAR_REGEX = Pattern.compile("[^a-zA-Z0-9]");

	public static boolean isValidEmailPattern(String input) {
		Matcher matcher = EMAIL_REGEX.matcher(input);
		return matcher.find();
	}

	public static boolean hasASpecialChar(String input) {
		Matcher matcher = SPECIAL_CHAR_REGEX.matcher(input);
		return matcher.find();
	}

	public static boolean isAtleastEighteen(Date dob) {
		LocalDate birthday = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();

		Period period = Period.between(birthday, today);
		int age = period.getYears();

		return (LocalDate.from(birthday)).until(LocalDate.now(), ChronoUnit.YEARS) >= 18;
	}

	public static boolean isStringNonEmpty(String name) {
		return !name.isEmpty();
	}

	public static boolean isCreditCardNumberValid(String cardNumber) {
		boolean[] dbl = { false };
		return cardNumber.replaceAll("\\s+", "").chars().map(c -> Character.digit((char) c, 10))
				.map(i -> ((dbl[0] = !dbl[0])) ? (((i * 2) > 9) ? (i * 2) - 9 : i * 2) : i).sum() % 10 == 0;
	}
	
	public static boolean isCreditCardValid(CreditCard card) {
		return (isCreditCardNumberValid(card.getCreditCardNo()) && card.getExpiry().after(new Date()));
	}

}
