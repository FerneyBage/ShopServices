package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.OrderDto.NewOrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;

import java.util.List;

public interface IOrderService {
    OrderDto getOrderById(long Id);
    List<OrderDto> getAllOder();
    List<OrderDto> getOrderByCustomer(Long customerId);
    OrderDto AddOrder(NewOrderDto newOrderDto);
}
