package com.sapient.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.utils.EncryptDecrypt;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDBService dbService;
	
	/**
	 * 
	 * @param Takes customer as input
	 * @return List of credit cards of the customer
	 */
	public List<CreditCard> getAllCreditCards(Customer customer) {
		List<CreditCard> encryptedCards = null;
		encryptedCards = this.dbService.getAllCards(customer.getEmail());
		
		List<CreditCard> decryptedCards = new ArrayList<>();
		
		for(CreditCard card : encryptedCards) {
			CreditCard decryptedCard = new CreditCard();
			String cardNumber = EncryptDecrypt.decrypt(card.getCreditCardNo());
			decryptedCard.setCreditCardNo(cardNumber);
			decryptedCard.setCvv(card.getCvv());
			decryptedCard.setExpiry(card.getExpiry());
			decryptedCard.setStatus(card.getStatus());
			
			decryptedCards.add(decryptedCard);
		}
		
		return decryptedCards;
	}




}
