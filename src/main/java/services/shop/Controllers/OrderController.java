package services.shop.Controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.shop.services.contract.IOrderService;

@RestController
@RequestMapping("/api/v1/products")
public class OrderController {
    private IOrderService _orderService;
    public OrderController(IOrderService orderService){
        _orderService = orderService;
    }
}
