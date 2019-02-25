package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beta.entities.Dog;
import beta.repositories.DogRepository;

@Service
public class MySqlSvc {

	@Autowired
	DogRepository dogRepository;
	
	@Transactional
	public void execute() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		dogRepository.save(new Dog("TOBY"));
		
		//ROLLBACK
		//if (true) throw new RuntimeException("PA VE");
		
		//dogRepository.save(new Dog("FIFI"));
		
		dogRepository.findAll()
			.forEach(c-> System.out.println(c));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	public void create(String name) {
		dogRepository.save(new Dog(name));		
	}
}
