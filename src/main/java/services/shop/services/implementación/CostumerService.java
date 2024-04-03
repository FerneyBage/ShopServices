package services.shop.services.implementación;

import services.shop.Dtos.MapperDto.ICostumerMapper;
import services.shop.repositories.ICustomerRepository;

public class CostumerService {
    private ICustomerRepository _customerRepository;
    private ICostumerMapper  _costumerMapper;
    public CostumerService(ICustomerRepository customerRepository, ICostumerMapper costumerMapper) {
        _customerRepository = customerRepository;
        _costumerMapper = costumerMapper;
    }

}
