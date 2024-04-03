package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;
import services.shop.entities.Customer;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ICustomerMapper {
    CustomerDto CostumerToCostumerDto(Customer costumer);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer NewCostumerDtoToCostumer(NewCustomerDto newCustomerDto);
    List<CustomerDto> CustomersToCustomersDto(List<Customer> customers);

}
