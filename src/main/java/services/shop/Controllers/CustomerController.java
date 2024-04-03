package services.shop.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;
import services.shop.services.contract.ICustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final ICustomerService _customerService;

    public CustomerController(ICustomerService customerService){
        _customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllOrders(){
        var customerDto = _customerService.GetAllCustomer();
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> AddCustomer(@RequestBody NewCustomerDto newCustomerDto){
        var CustomerCreated = _customerService.AddCustomer(newCustomerDto);
        return ResponseEntity.ok().body(CustomerCreated);
    }
}
