package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto getCustomerById(long id);
    CustomerDto AddCustomer(NewCustomerDto newCustomerDto);
    List<CustomerDto> GetAllCustomer();
    List<CustomerDto> getCustomerByEmail(String email);
    List<CustomerDto> getCustomerByCity(String city);
    CustomerDto updateCustomer(long id, NewCustomerDto productDto);
    CustomerDto deleteCustomer(long id);
}
