package services.shop.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.services.contract.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private IOrderService _orderService;
    public OrderController(IOrderService orderService){
        _orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long idOrder){
        OrderDto OrderDto = _orderService.getOrderById(idOrder);
        return ResponseEntity.ok().body(OrderDto);
    }

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        var OrdersDto = _orderService.getAllOder();
        return ResponseEntity.ok().body(OrdersDto);
    }

    @GetMapping("/customer/{customerId} ")
    public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable("customerId") Long customerId){
        var OrdersDto = _orderService.getOrderByCustomer(customerId);
        return ResponseEntity.ok().body(OrdersDto);
    }
}
