package services.shop.services.implementación;

import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.OrderDto.NewOrderDto;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderDto;
import services.shop.Dtos.EntitiesDto.StatusDto.StatusDto;
import services.shop.Dtos.MapperDto.IOrderMapper;
import services.shop.Exceptions.CustomerNotFoundException;
import services.shop.Exceptions.OrderNotFoundException;
import services.shop.Exceptions.StatusNotFoundException;
import services.shop.entities.Customer;
import services.shop.entities.Order;
import services.shop.entities.Status;
import services.shop.repositories.ICustomerRepository;
import services.shop.repositories.IOrderRepository;
import services.shop.repositories.IStatusRepository;
import services.shop.services.contract.IOrderService;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private IOrderRepository _orderRepository;
    private IStatusRepository _statusRepository;
    private ICustomerRepository _customerRepository;
    private IOrderMapper _orderMapper;

    public  OrderService(IOrderRepository orderRepository,IStatusRepository statusRepository,
                         ICustomerRepository customerRepository, IOrderMapper orderMapper){
        _orderRepository = orderRepository;
        _statusRepository = statusRepository;
        _customerRepository = customerRepository;
        _orderMapper = orderMapper;
    }
    public List<StatusDto> getAllStatuses(){
        List<Status> Statuses = _statusRepository.findAll();
        return _orderMapper.StatusesToStatusDtos(Statuses);
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
    public OrderDto AddOrder(NewOrderDto newOrderDto){
        Customer customer = _customerRepository.findById(newOrderDto.getCustomerId())
                            .orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        Status status = _statusRepository.findById(newOrderDto.getStatusId())
                            .orElseThrow(()-> new StatusNotFoundException("Status not found"));
        Order orderToCreade = _orderMapper.NewOrderDtoToOrder(newOrderDto);
        orderToCreade.setOrderDate(Date.from(ZonedDateTime.now().toInstant()));
        orderToCreade.setCustomer(customer);
        orderToCreade.setStatus(status);
        var orderCreaded = _orderRepository.save(orderToCreade);

        return _orderMapper.OrderToOrderDTO(orderCreaded);
    }


}
