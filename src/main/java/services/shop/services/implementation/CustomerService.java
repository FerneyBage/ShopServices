package services.shop.services.implementation;

import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;
import services.shop.Dtos.MapperDto.ICustomerMapper;
import services.shop.Exceptions.CustomerNotFoundException;
import services.shop.entities.Customer;
import services.shop.repositories.ICustomerRepository;
import services.shop.services.contract.ICustomerService;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository _customerRepository;
    private final ICustomerMapper  _customerMapper;
    public CustomerService(ICustomerRepository customerRepository, ICustomerMapper costumerMapper) {
        _customerRepository = customerRepository;
        _customerMapper = costumerMapper;
    }
    public CustomerDto getCustomerById(long id){
        Customer customer = _customerRepository.getReferenceById(id);
        return _customerMapper.CustomerToCostumerDto(customer);
    }
    public CustomerDto AddCustomer(NewCustomerDto newCustomerDto){
        var CustomerToCreate = _customerMapper.NewCostumerDtoToCostumer(newCustomerDto);
        var CustomerCreated = _customerRepository.save(CustomerToCreate);
        return  _customerMapper.CustomerToCostumerDto(CustomerCreated);
    }
    public List<CustomerDto> GetAllCustomer(){
        List<Customer> Customers = _customerRepository.findAll();
        return _customerMapper.CustomersToCustomersDto(Customers);
    }
    public List<CustomerDto> getCustomerByEmail(String email){
        List<Customer> customers =_customerRepository.findByEmail(email);
        return  _customerMapper.CustomersToCustomersDto(customers);
    }
    public List<CustomerDto> getCustomerByCity(String city){
        List<Customer> customers =_customerRepository.findByAddress(city);
        return  _customerMapper.CustomersToCustomersDto(customers);
    }
    public CustomerDto updateCustomer(long id, NewCustomerDto productDto) throws CustomerNotFoundException {
        Customer customer = _customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        _customerMapper.updateCustomerFromDto(productDto, customer);
        Customer updatedCustomer = _customerRepository.save(customer);
        return _customerMapper.CustomerToCostumerDto(updatedCustomer);
    }
    public CustomerDto deleteCustomer(long id) throws CustomerNotFoundException{
        Customer CustomerToDelete = _customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        _customerRepository.delete(CustomerToDelete);
        return _customerMapper.CustomerToCostumerDto(CustomerToDelete);
    }
}
