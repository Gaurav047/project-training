package com.sapient.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.CustomPOJO_A;
import com.sapient.customer.beans.CustomPOJO_B;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.service.CustomerDBService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private String userEmail = "c1@gmail.com";

	
	@Autowired
	CustomerDBService service;
	
//	@GetMapping(path = "/creditcards")
////	@PreAuthorize("hasRole('CUSTOMER_ROLE'")
//	public List<CreditCard> getCardsFromDB(Customer customer) {
//		return this.customerService.getAllCreditCards(customer);
//	}
//
//	@GetMapping(path = "/account")
//	public String getAccount(Customer c) {
//		return "";
//	}
//	
	
	
	/*
	 * sample function to authorize a end point
	 */
	@GetMapping(path = "/customer/{email}")
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	public Customer getCustomerByEmail(@PathVariable("email") String email)
	{
		return this.service.getCustomerByEmail(email);
	}
	
	
	/*
	 * 
	 * this api is done by chandan 
	 * Story 63
	 * 
	 */
	@GetMapping(path = "/customer/creditcards/expenses") /// api/customer/creditcards/expenses
	//@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	public List<CustomPOJO_A> getLastXnumberOfExpences(@PathParam(value = "") String transactions) {

		List<CreditCard> creditCards = this.service.getAllCards(this.userEmail);
		List<CustomPOJO_A> Lcp = new ArrayList<CustomPOJO_A>();
		for (CreditCard CC : creditCards) {
			List<CustomPOJO_B> lt = service.getTransactionByCcNumber(CC.getCreditCardNo(), Integer.parseInt(transactions));
			Lcp.add(new CustomPOJO_A(CC.getCreditCardNo(),lt));
		}
		return Lcp;
	}
	
	
	/*
	 * This api is developed by Gaurav Kumar
	 * It fetches the maximum amount spent for all credit cards for the transactions done in the last month.
	 * */
	@GetMapping(path = "/customer/creditcards/lastmonth/max") /// api/customer/creditcards/lastmonth/max
	//@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	public List<CustomPOJO_A> getMaxLastMonth() {
		List<CustomPOJO_A> Lcp = getLastXnumberOfExpenses("1");
		return Lcp;
	}
	
}
