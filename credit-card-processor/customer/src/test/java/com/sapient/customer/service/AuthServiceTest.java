package com.sapient.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.constants.Gender;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
	
//	@Mock
//	CustomerDAO cDao;
//	
//	@Mock
//	Customer customer;
//	
//	@InjectMocks
//	AuthService service;
	
	
	@Test 
	void validateCustomerFieldsTest() throws ParseException
	{
		AuthService service = new AuthService();

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<CreditCard> cards = new ArrayList<>();
		List<String> errorList = new ArrayList<>();
		
		cards.add(new CreditCard("4024007183207744", "033", sdf.parse("2021-12-12"), null));
		Customer customer = new Customer("101", "user.com", "p@ssword" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Email is badly formatted"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("4024007183207744", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("2221003013121770", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","user@.com", "p@ssword" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Email is badly formatted"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","user@email.com", "password" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Password should be minimum 6 characters long and should have at least a special character"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","user@email.com", "passw" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Password should be minimum 6 characters long and should have at least a special character"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","user@email.com", "pass@" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Password should be minimum 6 characters long and should have at least a special character"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","user@email.com", "password@1" , cards, "first name", "last name", sdf.parse("2020-09-09"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("You must be atleast 18 years old"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("36187907413820", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("30518353229615", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101",".com", "password@1" , cards, "first name", "last name", sdf.parse("2020-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Email is badly formatted", "You must be atleast 18 years old"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493","033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","e@email.com", "password@1" , cards, "", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("First Name can't be empty"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-01-12"), null));
		customer = new Customer("101","e@.com", "password12" , cards, "", "last name", sdf.parse("2020-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Email is badly formatted", "Password should be minimum 6 characters long and should have at least a special character", "You must be atleast 18 years old", "First Name can't be empty"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2020-09-29"), null));
		customer = new Customer("101","email@email.com", "passwo@rd12" , cards, "first name", "last name", sdf.parse("1997-12-12"), Gender.TRANSGENDER, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Please validate credit card ending with 4493"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2020-09-29"), null));
		cards.add(new CreditCard("348401061389395", "033", sdf.parse("2020-11-12"), null));
		customer = new Customer("101","email@email.com", "passwo@rd12" , cards, "first name", "last name", sdf.parse("1997-12-12"), Gender.TRANSGENDER, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList("Please validate credit card ending with 4493", "Please validate credit card ending with 9395"), errorList);
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("4024007183207744", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("2221003013121770", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","user@email.com", "p@ssword" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		errorList = service.validateCustomerFields(customer);
		assertEquals(Arrays.asList(), errorList);
	}
	
	@Test
	void insertCustomerToDBTest() throws ParseException {		
		AuthService service = new AuthService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
		List<CreditCard> cards = new ArrayList<>();
		
		cards.add(new CreditCard("4024007183207744", "033", sdf.parse("2021-12-12"), null));
		Customer customer = new Customer("101","user.com", "p@ssword" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		assertEquals("Email is badly formatted\n", service.registerCustomer(customer));
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101","user@email.com", "password" , cards, "first name", "last name", sdf.parse("1995-12-12"), Gender.MALE, null, false);
		assertEquals("Password should be minimum 6 characters long and should have at least a special character\n", service.registerCustomer(customer));
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("348401061389939", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("36187907413820", "033", sdf.parse("2021-12-12"), null));
		cards.add(new CreditCard("30518353229615", "033", sdf.parse("2021-12-12"), null));
		customer = new Customer("101",".com", "password@1" , cards, "first name", "last name", sdf.parse("2020-12-12"), Gender.MALE, null, false);
		assertEquals("Email is badly formatted\nYou must be atleast 18 years old\n", service.registerCustomer(customer));
		
		cards = new ArrayList<>();
		cards.add(new CreditCard("3544502096784493", "033", sdf.parse("2020-09-29"), null));
		cards.add(new CreditCard("348401061389395", "033", sdf.parse("2020-11-12"), null));
		customer = new Customer("101","email@email.com", "passwo@rd12" , cards, "first name", "last name", sdf.parse("1997-12-12"), Gender.TRANSGENDER, null, false);
		assertEquals("Please validate credit card ending with 9395\n", service.registerCustomer(customer));
		
		
	}

}
