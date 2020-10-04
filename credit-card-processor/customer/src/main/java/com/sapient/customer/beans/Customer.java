package com.sapient.customer.beans;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;
import com.sapient.customer.constants.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "customers")
public class Customer{

	@Id
	private String id;

	@NotBlank
	@Size(min = 6)
	@Email
	@Indexed(unique = true, name = "email_index_unique")
	private String email;
	@NotBlank
	private String password;
	private List<CreditCard> creditCards;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private Gender gender;
	private Address address;
	private Boolean status;
	private List<Link> links;
	
	public Customer(String id, @NotBlank @Size(min = 6) @Email String email, @NotBlank String password,
			List<CreditCard> creditCards, String firstName, String lastName, Date dateOfBirth, Gender gender,
			Address address, Boolean status) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.creditCards = creditCards;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.status = status;
	}
	
	
}
