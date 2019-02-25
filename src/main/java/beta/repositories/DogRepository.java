package beta.repositories;

import org.springframework.data.repository.CrudRepository;

import beta.entities.Dog;

public interface DogRepository extends CrudRepository<Dog, Long> {

}
