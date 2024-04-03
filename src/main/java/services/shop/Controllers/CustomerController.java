package services.shop.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.CustomersDto.NewCustomerDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.services.contract.ICustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final ICustomerService _customerService;

    public CustomerController(ICustomerService customerService){
        _customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id){
        var customerDto = _customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customerDto);
    }
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomer(){
        var customerDto = _customerService.GetAllCustomer();
        return ResponseEntity.ok().body(customerDto);
    }
    @PostMapping()
    public ResponseEntity<CustomerDto> AddCustomer(@RequestBody NewCustomerDto newCustomerDto){
        var CustomerCreated = _customerService.AddCustomer(newCustomerDto);
        return ResponseEntity.ok().body(CustomerCreated);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<CustomerDto>> getCustomerById(@PathVariable("email") String email){
        var customerDto = _customerService.getCustomerByEmail(email);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/city")
    public ResponseEntity<List<CustomerDto>> getCustomerByCityName(@RequestParam("cityName") String cityName){
        var customerDto = _customerService.getCustomerByCity(cityName);
        return ResponseEntity.ok().body(customerDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> putCustomer(@PathVariable("id") Long id, @RequestBody NewCustomerDto newCustomerDto){
        CustomerDto customerDto = _customerService.updateCustomer(id, newCustomerDto);
        return ResponseEntity.ok().body(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> putProduct(@PathVariable("id") Long id){
        CustomerDto customerDto = _customerService.deleteCustomer(id);
        return ResponseEntity.ok().body(customerDto);
    }
}
