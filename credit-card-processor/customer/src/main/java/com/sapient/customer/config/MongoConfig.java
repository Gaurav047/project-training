package com.sapient.customer.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.sapient.customer.beans.CreditCard;
import com.sapient.customer.beans.Customer;
import com.sapient.customer.beans.Transaction;
import com.sapient.customer.constants.Gender;
import com.sapient.customer.repo.CustomerRepository;
import com.sapient.customer.repo.ICreditCardRepository;
import com.sapient.customer.repo.ITransactionRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
public class MongoConfig {

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository userRepo, ICreditCardRepository ccRepo,
			ITransactionRepository tRepo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return String -> {
			/*
			ccRepo.save(new CreditCard("112233", "123", sdf.parse("2024-09-09"), null));
			ccRepo.save(new CreditCard("223344", "189", sdf.parse("2023-04-12"), null));
			ccRepo.save(new CreditCard("334455", "985", sdf.parse("2029-02-23"), null));

			ccRepo.save(new CreditCard("445566", "884", sdf.parse("2022-03-14"), null));
			ccRepo.save(new CreditCard("556677", "762", sdf.parse("2025-12-16"), null));
			*/

			List<CreditCard> user1CreditCard = new ArrayList<CreditCard>();
			List<CreditCard> user2CreditCard = new ArrayList<CreditCard>();

			user1CreditCard.add(new CreditCard("112233", "123", sdf.parse("2024-09-09"), null));
			user1CreditCard.add(new CreditCard("223344", "189", sdf.parse("2023-04-12"), null));
			user1CreditCard.add(new CreditCard("334455", "985", sdf.parse("2029-02-23"), null));

			user2CreditCard.add(new CreditCard("445566", "884", sdf.parse("2022-03-14"), null));
			user2CreditCard.add(new CreditCard("556677", "762", sdf.parse("2025-12-16"), null));

			userRepo.save(new Customer("201", "c1@gmail.com", "A1B2C3", user1CreditCard, "Chandan", "Reddy",
					sdf.parse("1998-09-09"), Gender.MALE, null, false));

			userRepo.save(new Customer("202", "s2@gmail.com", "D1D3C4", user2CreditCard, "Suresh", "Reddy",
					sdf.parse("1998-09-09"), Gender.FEMALE, null, false));
			
			userRepo.save(new Customer("203", "a3@gmail.com", "D1D3C4", null, "Nani", "Kumar",
					sdf.parse("1998-09-09"), Gender.FEMALE, null, false));


			tRepo.save(new Transaction("901", "112233", 234.6, "debit", "online", LocalDate.of(2020, 9, 19),
					LocalTime.of(13, 34, 42), "hyd", "192.168.32.109", "success", "501", "for food"));
			tRepo.save(new Transaction("902", "112233", 134.3, "debit", "online", LocalDate.of(2020, 10, 12),
					LocalTime.of(1, 35, 42), "hyd", "192.168.32.109", "success", "502", "paying bill"));
			tRepo.save(new Transaction("903", "112233", 33223.5, "debit", "atm", LocalDate.of(2020, 7, 24),
					LocalTime.of(15, 34, 42), "hyd", "192.168.44.12", "success", "503", "for debt"));

			tRepo.save(new Transaction("904", "223344", 360.0, "debit", "online", LocalDate.of(2020, 8, 2),
					LocalTime.of(3, 8, 42), "hyd", "192.168.32.109", "success", "504", "for A1"));
			tRepo.save(new Transaction("907", "223344", 124.33, "debit", "online", LocalDate.of(2020, 10, 6),
					LocalTime.of(1, 34, 42), "hyd", "192.168.32.109", "success", "505", "for A2"));

			tRepo.save(new Transaction("905", "334455", 12000.0, "debit", "pos", LocalDate.of(2020, 7, 7),
					LocalTime.of(13, 11, 42), "hyd", "192.168.32.109", "success", "506", "for A3"));
			tRepo.save(new Transaction("906", "334455", 9856.8, "debit", "atm", LocalDate.of(2020, 2, 9),
					LocalTime.of(23, 33, 42), "hyd", "192.168.32.109", "success", "507", "for A4"));

			tRepo.save(new Transaction("908", "445566", 2000.0, "debit", "online", LocalDate.of(2020, 7, 19),
					LocalTime.of(13, 34, 42), "hyd", "192.168.32.109", "success", "508", "for A5"));
		};

	}

}
