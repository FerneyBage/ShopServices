package services.shop.services.implementación;

import services.shop.Dtos.MapperDto.IOrderMapper;
import services.shop.repositories.IOrderRepository;

public class OrderService {
    private IOrderRepository _orderRepository;
    private IOrderMapper _OrderMapper;

    public  OrderService(IOrderRepository orderRepository, IOrderMapper orderMapper){
        _orderRepository = orderRepository;
        _OrderMapper = orderMapper;
    }



}
