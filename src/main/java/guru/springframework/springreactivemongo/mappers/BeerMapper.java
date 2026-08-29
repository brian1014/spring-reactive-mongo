package guru.springframework.springreactivemongo.mappers;

import guru.springframework.springreactivemongo.domain.Beer;
import guru.springframework.springreactivemongo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDTO entityToDto(Beer beer);

    Beer dtoToEntity(BeerDTO beerDTO);
}
