package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alpha.entities.Customer;
import alpha.repositories.CustomerRepository;

@Service
public class PostgreSQLSvc {

	@Autowired
	CustomerRepository customerRepository;
	
	
	
	@Transactional
	public void execute() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		customerRepository.save(new Customer("ALPHA", "OCHOA"));
		
		customerRepository.save(new Customer("_MILTON", "OCHOA"));
		
		customerRepository.findByLastName("OCHOA")
			.forEach(c-> System.out.println(c));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	public void create(String firstName, String lastName) {
		customerRepository.save(new Customer(firstName, lastName));
	}
}
