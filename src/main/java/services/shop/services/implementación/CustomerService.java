package services.shop.services.implementación;

import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;
import services.shop.Dtos.MapperDto.ICustomerMapper;
import services.shop.entities.Customer;
import services.shop.repositories.ICustomerRepository;
import services.shop.services.contract.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private ICustomerRepository _customerRepository;
    private ICustomerMapper  _customerMapper;
    public CustomerService(ICustomerRepository customerRepository, ICustomerMapper costumerMapper) {
        _customerRepository = customerRepository;
        _customerMapper = costumerMapper;
    }

    public CustomerDto AddCustomer(NewCustomerDto newCustomerDto){
        var CustomerToCreate = _customerMapper.NewCostumerDtoToCostumer(newCustomerDto);
        var CustomerCreated = _customerRepository.save(CustomerToCreate);
        return  _customerMapper.CostumerToCostumerDto(CustomerCreated);
    }

    public List<CustomerDto> GetAllCustomer(){
        List<Customer> Customers = _customerRepository.findAll();
        return _customerMapper.CustomersToCustomersDto(Customers);
    }

}
