package services.shop.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.services.contract.IOrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private IOrderService _orderService;
    public OrderController(IOrderService orderService){
        _orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long idProduct){
        OrderDto OrderDto = _orderService.getOrderById(idProduct);
        return ResponseEntity.ok().body(OrderDto);
    }
}
