package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.OrderItemDto.NewOrderItemDto;
import services.shop.Dtos.EntitiesDto.OrderItemDto.OrderItemDto;

import java.util.List;

public interface IOrderItemService {
    OrderItemDto getOrderItemById(long id);
    List<OrderItemDto> getAllOrderItem();
    List<OrderItemDto> getOrderItemByOrderId(Long orderId);
    List<OrderItemDto> getOrderItemByProductId(Long productId);
    OrderItemDto addOrderItem(NewOrderItemDto newOrderItemDto);
    OrderItemDto UpdateOrderItem(long id, NewOrderItemDto newOrderItemDto);
    OrderItemDto deleteOrderItem(long id);
}
