package guru.springframework.springreactivemongo.services;

import guru.springframework.springreactivemongo.domain.Beer;
import guru.springframework.springreactivemongo.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
    Mono<BeerDTO> findFirstByBeerName(String beerName);

    Flux<BeerDTO> findByBeerStyle(String beerStyle);

    Flux<BeerDTO> listBeers();

    Mono<BeerDTO> saveBeer(Mono<BeerDTO> dto);

    Mono<BeerDTO> saveBeer(BeerDTO dto);

    Mono<BeerDTO> getById(String beerId);

    Mono<BeerDTO> updateBeer(String beerId, BeerDTO dto);

    Mono<BeerDTO> patchBeer(String beerId, BeerDTO dto);

    Mono<Void> deleteBeerById(String beerId);

}
