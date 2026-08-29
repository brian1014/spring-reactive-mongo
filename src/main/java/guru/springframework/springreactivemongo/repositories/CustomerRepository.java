package guru.springframework.springreactivemongo.repositories;

import guru.springframework.springreactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
