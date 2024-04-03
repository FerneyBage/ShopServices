package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.OrderDto.NewOrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.StatusDto.StatusDto;

import java.util.List;

public interface IOrderService {
    List<StatusDto> getAllStatuses();
    OrderDto getOrderById(long Id);
    List<OrderDto> getAllOder();
    List<OrderDto> getOrderByCustomer(Long customerId);
    OrderDto AddOrder(NewOrderDto newOrderDto);
}
