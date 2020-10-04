package com.sapient.customer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sapient.customer.beans.CreditCard;

@Repository
public interface ICreditCardRepository extends MongoRepository<CreditCard, String> {

}
