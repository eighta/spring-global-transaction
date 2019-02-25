package alpha.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import alpha.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}