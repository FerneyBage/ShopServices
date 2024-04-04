package services.shop.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.shop.Dtos.EntitiesDto.OrderItemDto.NewOrderItemDto;
import services.shop.Dtos.EntitiesDto.OrderItemDto.OrderItemDto;
import services.shop.services.contract.IOrderItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {
    public final IOrderItemService _orderItemService;

    public OrderItemController(IOrderItemService orderItemService) {
        _orderItemService = orderItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable("id") Long idOrderItem){
        OrderItemDto orderItemDto = _orderItemService.getOrderItemById(idOrderItem);
        return ResponseEntity.ok().body(orderItemDto);
    }

    @GetMapping()
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems(){
        List<OrderItemDto> orderItemDtos = _orderItemService.getAllOrderItem();
        return ResponseEntity.ok().body(orderItemDtos);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDto>> getOrderItemByOrderId(@PathVariable("orderId") Long orderId) {
        List<OrderItemDto> orderItemDtos = _orderItemService.getOrderItemByOrderId(orderId);
        return ResponseEntity.ok().body(orderItemDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderItemDto>> getOrderItemByProductId(@PathVariable("productId") Long productId) {
        List<OrderItemDto> orderItemDtos = _orderItemService.getOrderItemByProductId(productId);
        return ResponseEntity.ok().body(orderItemDtos);
    }

    @PostMapping()
    public  ResponseEntity<OrderItemDto> addOrderItemDto(@RequestBody() NewOrderItemDto newOrderItemDto){
        OrderItemDto orderItemDto = _orderItemService.addOrderItem(newOrderItemDto);
        return ResponseEntity.ok().body(orderItemDto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<OrderItemDto> UpdateOrderItem(@PathVariable("id") Long idOrderItem,@RequestBody() NewOrderItemDto newOrderItemDto ){
        OrderItemDto orderItemDto = _orderItemService.UpdateOrderItem( idOrderItem, newOrderItemDto);
        return ResponseEntity.ok().body(orderItemDto);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<OrderItemDto> DeleteOrderItem(@PathVariable("id") Long idOrderItem){
        OrderItemDto orderItemDto = _orderItemService.deleteOrderItem( idOrderItem);
        return ResponseEntity.ok().body(orderItemDto);
    }

}
