package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import services.shop.Dtos.EntitiesDto.CustomersDto.CostumerDto;
import services.shop.entities.Customer;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ICostumerMapper {
    CostumerDto CostumerToCostumerDto(Customer costumer);
    List<CostumerDto> CustomersToCustomersDto(List<Customer> customers);

}
