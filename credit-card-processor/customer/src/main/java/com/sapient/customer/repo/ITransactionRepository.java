package com.sapient.customer.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sapient.customer.beans.Transaction;

@Repository
public interface ITransactionRepository extends MongoRepository<Transaction, String> {
	@Query(value = "", fields = "")
	public List<Transaction> findByCcNumber(String ccNumber, Sort sort); // scope for change

}