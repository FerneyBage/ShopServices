package services.shop.Dtos.MapperDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.entities.Customer;
import services.shop.entities.Product;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ICustomerMapper {
    CustomerDto CustomerToCostumerDto(Customer costumer);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer NewCostumerDtoToCostumer(NewCustomerDto newCustomerDto);
    List<CustomerDto> CustomersToCustomersDto(List<Customer> customers);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateCustomerFromDto(NewCustomerDto dto, @MappingTarget Customer customer);

}
