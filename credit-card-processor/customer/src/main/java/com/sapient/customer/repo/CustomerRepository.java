package com.sapient.customer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sapient.customer.beans.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
	public Customer findByEmail(String email);
	public Boolean existsByEmail(String email);
}
