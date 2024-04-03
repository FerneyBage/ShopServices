package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.entities.Customer;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ICostumerMapper {
    CustomerDto CostumerToCostumerDto(Customer costumer);
    List<CustomerDto> CustomersToCustomersDto(List<Customer> customers);

}
