package com.sapient.customer.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.CustomPOJO_B;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.beans.Transaction;
import com.sapient.customer.repo.CustomerRepository;
import com.sapient.customer.repo.ITransactionRepository;

@Service
public class CustomerDBService {
	
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private ITransactionRepository trepo;
	
	public Optional<Customer> getCustomerById(String id)
	{
		return this.repository.findById(id);
	}
	
	public Customer getCustomerByEmail(String email)
	{
		return this.repository.findByEmail(email);
	}

	public Customer insertCustomer(Customer customer)
	{
		return this.repository.insert(customer);
	}
	
	public Customer updateCustomer(Customer customer)
	{
		return this.repository.save(customer);
	}
	
	public void deleteCustomerById(String id)
	{
		this.repository.deleteById(id);
	}
	
	public List<Customer> getAllCustomers(){
		return this.repository.findAll();
	}
	
	public boolean isCustomerExists(String email)
	{
		return this.repository.existsByEmail(email);
	}
	
	public List<CreditCard> getAllCards(String email) {
		List<CreditCard> cards = new ArrayList<>();


		cards = getCustomerByEmail(email).getCreditCards();

		return cards;
	}
	
	//transaction part written by chandan story number 63
	
	public Transaction getTransactionById(String transactionNumber) {
		Optional<Transaction> optionalObj = this.trepo.findById(transactionNumber);
		return optionalObj.get(); // scope for change
	}

	
	public List<CustomPOJO_B> getTransactionByCcNumber(String ccNumber, int number) {
		Sort sort = Sort.by(Sort.Direction.DESC, "transactionAmount");
		List<Transaction> tr = this.trepo.findByCcNumber(ccNumber, sort);
		List<Transaction> ret = new ArrayList<Transaction>();
		for (Transaction temp : tr) {
			if (temp.getTransactionDate().getMonth().compareTo(LocalDate.now().getMonth()) < 0) {
				ret.add(temp);
			}
		}
		List<CustomPOJO_B> cp1 = new ArrayList<CustomPOJO_B>();
		ret = ret.subList(0, Math.min(ret.size(), number));
		for (Transaction t1 : ret) {
			CustomPOJO_B c1 = new CustomPOJO_B(
					t1.getTransactionDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US),
					t1.getTransactionAmount(), t1.getTransactionDescription());
			cp1.add(c1);
		}
		return cp1;
	}
	
	public JSONObject getLastMonthMaxTransactionWithMerchant(String ccNumber){
		Sort sort = Sort.by(Sort.Direction.DESC, "transactionAmount");
		List<Transaction> tr = this.trepo.findByCcNumber(ccNumber, sort);
		Transaction ret = null;
		for (Transaction temp : tr) {
			if (temp.getTransactionDate().getMonth().compareTo(LocalDate.now().getMonth()) < 0) {
				ret = temp;
				break;
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("credit_card", ccNumber);
		jo.put("month", ret.getTransactionDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US));
		jo.put("amount", ret.getTransactionAmount());
		jo.put("merchant", ret.getMerchantId());
		return jo;
	}
	
	public JSONObject getLastMonthMaxTransactionWithoutMerchant(String ccNumber){
		JSONObject jo = getLastMonthMaxTransactionWithMerchant(ccNumber);
		jo.remove("merchant");
		return jo;
	}

}
