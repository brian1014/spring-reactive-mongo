package guru.springframework.springreactivemongo.mappers;

import guru.springframework.springreactivemongo.domain.Customer;
import guru.springframework.springreactivemongo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO entityToDto(Customer customer);

    Customer dtoToEntity(CustomerDTO customerDTO);
}
