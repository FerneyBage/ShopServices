package services.shop.services.implementación;

import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.MapperDto.IOrderMapper;
import services.shop.Exceptions.OrderNotFoundException;
import services.shop.entities.Order;
import services.shop.repositories.IOrderRepository;
import services.shop.repositories.IStatusRepository;
import services.shop.services.contract.IOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private IOrderRepository _orderRepository;
    private IStatusRepository _statusRepository;
    private IOrderMapper _orderMapper;

    public  OrderService(IOrderRepository orderRepository,IStatusRepository statusRepository, IOrderMapper orderMapper){
        _orderRepository = orderRepository;
        _statusRepository = statusRepository;
        _orderMapper = orderMapper;
    }

    public OrderDto getOrderById(long Id) throws OrderNotFoundException{
        Order order = _orderRepository.findById(Id).orElseThrow(()-> new OrderNotFoundException("Order not found"));
        return _orderMapper.OrderToOrderDTO(order);
    }

    public List<OrderDto> getAllOder(){
        List<Order> Orders = _orderRepository.findAll();
        return _orderMapper.OrdersToOrderDtoDTOs(Orders);
    }

    public List<OrderDto> getOrderByCustomer(Long customerId){
        List<Order> Orders = _orderRepository.findByCustomerId(customerId);
        return _orderMapper.OrdersToOrderDtoDTOs(Orders);
    }

}
