package guru.springframework.springreactivemongo.services;

import guru.springframework.springreactivemongo.mappers.BeerMapper;
import guru.springframework.springreactivemongo.model.BeerDTO;
import guru.springframework.springreactivemongo.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public Flux<BeerDTO> findByBeerStyle(String beerStyle) {
        return beerRepository.findByBeerStyle(beerStyle)
                .map(beerMapper::entityToDto);
    }

    @Override
    public Mono<BeerDTO> findFirstByBeerName(String beerName) {
        return beerRepository.findFirstByBeerName(beerName)
                .map(beerMapper::entityToDto);
    }

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::entityToDto);
    }

    @Override
    public Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDto) {
        return beerDto.map(beerMapper::dtoToEntity)
                .flatMap(beerRepository::save)
                .map(beerMapper::entityToDto);
    }

    @Override
    public Mono<BeerDTO> saveBeer(BeerDTO beerDTO) {
        return beerRepository.save(beerMapper.dtoToEntity(beerDTO))
                .map(beerMapper::entityToDto);
    }

    @Override
    public Mono<BeerDTO> getById(String beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::entityToDto);
    }

    @Override
    public Mono<BeerDTO> updateBeer(String beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    //update properties
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setPrice(beerDTO.getPrice());
                    foundBeer.setUpc(beerDTO.getUpc());
                    foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());

                    return foundBeer;
                }).flatMap(beerRepository::save)
                .map(beerMapper::entityToDto);
    }

    @Override
    public Mono<BeerDTO> patchBeer(String beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    if(StringUtils.hasText(beerDTO.getBeerName())){
                        foundBeer.setBeerName(beerDTO.getBeerName());
                    }

                    if(StringUtils.hasText(beerDTO.getBeerStyle())){
                        foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    }

                    if(beerDTO.getPrice() != null){
                        foundBeer.setPrice(beerDTO.getPrice());
                    }

                    if(StringUtils.hasText(beerDTO.getUpc())){
                        foundBeer.setUpc(beerDTO.getUpc());
                    }

                    if(beerDTO.getQuantityOnHand() != null){
                        foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    }
                    return foundBeer;
                }).flatMap(beerRepository::save)
                .map(beerMapper::entityToDto);
    }

    @Override
    public Mono<Void> deleteBeerById(String beerId) {
        return beerRepository.deleteById(beerId);
    }
}
