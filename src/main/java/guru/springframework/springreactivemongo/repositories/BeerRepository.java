package guru.springframework.springreactivemongo.repositories;

import guru.springframework.springreactivemongo.domain.Beer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
    Mono<Beer> findFirstByBeerName(String beerName);

    Flux<Beer> findByBeerStyle(String beerStyle);
}
