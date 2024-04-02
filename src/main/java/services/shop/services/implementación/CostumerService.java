package services.shop.services.implementación;

import services.shop.Dtos.MapperDto.ICostumerMapper;
import services.shop.repositories.ICustomerRepository;

public class CostumerService {
    private ICustomerRepository _CustomerRepository;
    private ICostumerMapper  _iCostumerMapper;
    public CostumerService(ICustomerRepository customerRepository, ICostumerMapper iCostumerMapper) {
        _CustomerRepository = customerRepository;
        _iCostumerMapper = iCostumerMapper;
    }

}
