package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;

public interface IOrderService {
    OrderDto getOrderById(long Id);
}
