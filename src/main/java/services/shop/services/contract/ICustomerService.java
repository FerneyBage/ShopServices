package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto AddCustomer(NewCustomerDto newCustomerDto);
    List<CustomerDto> GetAllCustomer();
}
