package services.shop.services.implementación;

import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.MapperDto.ICostumerMapper;
import services.shop.repositories.ICustomerRepository;

public class CustumerService {
    private ICustomerRepository _customerRepository;
    private ICostumerMapper  _costumerMapper;
    public CustumerService(ICustomerRepository customerRepository, ICostumerMapper costumerMapper) {
        _customerRepository = customerRepository;
        _costumerMapper = costumerMapper;
    }

    public CustomerDto

}
