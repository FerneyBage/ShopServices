package services.shop.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.shop.Dtos.EntitiesDto.OrderDto.NewOrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.Dtos.EntitiesDto.StatusDto.StatusDto;
import services.shop.services.contract.IOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private IOrderService _orderService;
    public OrderController(IOrderService orderService){
        _orderService = orderService;
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<StatusDto>> getALlStatuses(){
        List<StatusDto> StatusDtos = _orderService.getAllStatuses();
        return ResponseEntity.ok().body(StatusDtos);
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

    @PostMapping()
    public ResponseEntity<OrderDto> PostOrder(@RequestBody NewOrderDto newOrderDto){
        var OrdersDto = _orderService.AddOrder(newOrderDto);
        return ResponseEntity.ok().body(OrdersDto);
    }
}
