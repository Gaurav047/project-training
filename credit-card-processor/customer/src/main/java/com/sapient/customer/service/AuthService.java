package com.sapient.customer.service;


//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.controller.AuthController;
import com.sapient.customer.payload.request.LoginRequest;
import com.sapient.customer.payload.response.JwtResponse;
import com.sapient.customer.payload.response.MessageResponse;
import com.sapient.customer.security.jwt.JwtUtils;
import com.sapient.customer.security.services.UserDetailsImpl;
import com.sapient.customer.utils.EncryptDecrypt;
import com.sapient.customer.utils.Utility;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class AuthService {
	
	private static final int MIN_EMAIL_LENGTH = 6;
	private static final int  MIN_PASSWORD_LENGTH = 6;
	
	@Autowired
	CustomerDBService dbService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	ObjectMapper mapper;
	
	
	public ResponseEntity<?> loginCustomer(LoginRequest loginRequest){
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
	
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			Customer customer = dbService.getCustomerByEmail(userDetails.getEmail());
			
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
			
			customer.setCreditCards(decryptedCards);
			
			MessageResponse<String> message;
			if (!customer.getStatus()) {
				message = new MessageResponse<String>("Welcome to Credi-Buddy");
				customer.setStatus(true);
				Link temp1 = linkTo(AuthController.class).slash("creditcards").withSelfRel();
				Link temp = linkTo(AuthController.class).slash("account").withSelfRel();
				List<Link> links = customer.getLinks();
				links.add(temp);
				links.add(temp1);
				customer.setLinks(links);
				dbService.updateCustomer(customer);
			} else {
				message = null;
			}
	
			return ResponseEntity.ok(new JwtResponse(jwt, customer, message));
		} catch ( BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse<String>("Please enter valid credentials"));
		}
	}
	
	/**
	 * Validate and then insert user to db;
	 * @param customer
	 * @return list acknowledgement (String)
	 */
	public String registerCustomer(Customer customer) {
		List<String> errors = this.validateCustomerFields(customer);
		if(errors.isEmpty()) {
			try{
				customer.setPassword(encoder.encode(customer.getPassword()));
				List<CreditCard> encryptedCards = customer.getCreditCards().stream().map(card -> {
					card.setCreditCardNo(EncryptDecrypt.encrypt((card.getCreditCardNo())));
					return card;
				}).collect(Collectors.toList());
				
				customer.setCreditCards(encryptedCards);
				customer.setLinks(new ArrayList<Link>());
				customer.setStatus(false);
				dbService.insertCustomer(customer);
				return "Congratulations for registration"; 		
			} catch(DuplicateKeyException e) {
				throw new DuplicateKeyException("User with this email already exists");
			} catch (Exception e) {
				return "Bad Request";
			}
		}else {
			String response = "";
			for(String error : errors) {
				response += error + "\n";
			}
			
			return response;
		}
	}
	
	/**
	 * 
	 * @param customer
	 * @return list of errors
	 */
	public List<String> validateCustomerFields(Customer customer) {
		List<String> errorList = new ArrayList<>();
		String email = customer.getEmail();
		String password = customer.getPassword();
		String firstName = customer.getFirstName();
		Date dob = customer.getDateOfBirth();
		List<CreditCard> cards = customer.getCreditCards();
		
		if(!this.isEmailValid(email)) {
			errorList.add("Email is badly formatted");
		}
		
		if(!this.isPasswordValid(password)) {
			errorList.add("Password should be minimum 6 characters long and should have at least a special character");
		}
		
		if(!Utility.isAtleastEighteen(dob)) {
			errorList.add("You must be atleast 18 years old");
		}
		
		if(!Utility.isStringNonEmpty(firstName)) {
			errorList.add("First Name can't be empty");
		}
		
		try {
			for(int i=0; i<cards.size(); i++) {
				if(!Utility.isCreditCardValid(cards.get(i))) {
					String cardNumber = cards.get(i).getCreditCardNo().toString().replaceAll("\\s+", "");
					int length = cardNumber.length();
					errorList.add("Please validate credit card ending with " + cardNumber.substring(length-4));
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			errorList.add("Please add a credit card");
		}
		
		return errorList;
	}
	
	private boolean isEmailValid(String email) {
		int length = email.length();
		return (length >= MIN_EMAIL_LENGTH && Utility.isValidEmailPattern(email));
	}
	
	private boolean isPasswordValid(String password) {
		int length = password.length();
		return (length >= MIN_PASSWORD_LENGTH && Utility.hasASpecialChar(password));
	}


}
