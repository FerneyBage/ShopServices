package services.shop.services.implementation;

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
import services.shop.repositories.*;
import services.shop.services.contract.IOrderService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final IOrderRepository _orderRepository;
    private final  IStatusRepository _statusRepository;
    private final  ICustomerRepository _customerRepository;
    private final IOrderItemRepository _orderItemRepository;
    private final IPaymentRepository _paymentRepository;
    private final  IShippingDetailRepository _shippingDetailRepository;
    private final  IOrderMapper _orderMapper;

    public  OrderService(IOrderRepository orderRepository, IStatusRepository statusRepository,
                         ICustomerRepository customerRepository, IOrderItemRepository orderItemRepository,
                         IPaymentRepository paymentRepository, IShippingDetailRepository shippingDetailRepository,
                         IOrderMapper orderMapper){
        _orderRepository = orderRepository;
        _statusRepository = statusRepository;
        _customerRepository = customerRepository;
        _orderItemRepository = orderItemRepository;
        _paymentRepository = paymentRepository;
        _shippingDetailRepository = shippingDetailRepository;
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
    public OrderDto AddOrder(NewOrderDto newOrderDto) throws  CustomerNotFoundException, StatusNotFoundException{
        Customer customer = _customerRepository.findById(newOrderDto.getCustomerId())
                            .orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        Status status = _statusRepository.findById(newOrderDto.getStatusId())
                            .orElseThrow(()-> new StatusNotFoundException("Status not found"));
        Order orderToCreate = _orderMapper.NewOrderDtoToOrder(newOrderDto);
        orderToCreate.setOrderDate(LocalDateTime.now());
        orderToCreate.setCustomer(customer);
        orderToCreate.setStatus(status);
        var orderCreated = _orderRepository.save(orderToCreate);

        return _orderMapper.OrderToOrderDTO(orderCreated);
    }
    public List<OrderDto> getOrdersByDate(LocalDateTime StarDate, LocalDateTime EndDate){
        List<Order> orders = _orderRepository.findByOrderDateBetween(StarDate,EndDate);
        return _orderMapper.OrdersToOrderDtoDTOs(orders);
    }
    public  OrderDto updateOrder(Long Id, NewOrderDto newOrderDto){
        Order orderToUpdate = _orderRepository.findById(Id)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        Customer customer = _customerRepository.findById(newOrderDto.getCustomerId())
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        Status status = _statusRepository.findById(newOrderDto.getStatusId())
                .orElseThrow(()-> new StatusNotFoundException("Status not found"));
        _orderMapper.updateOrderFromDto(newOrderDto, orderToUpdate);
        orderToUpdate.setStatus(status);
        orderToUpdate.setCustomer(customer);
        Order updatedOrder = _orderRepository.save(orderToUpdate);
        return _orderMapper.OrderToOrderDTO(updatedOrder);
    }
    public OrderDto deleteOrder(Long Id){
        Order orderToDelete = _orderRepository.findById(Id)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        if(orderToDelete.getOrderItems() != null) _orderItemRepository.deleteAll(orderToDelete.getOrderItems());
        if(orderToDelete.getPayment() != null) _paymentRepository.delete(orderToDelete.getPayment());
        if(orderToDelete.getShippingDetail() != null) _shippingDetailRepository.delete(orderToDelete.getShippingDetail());
        _orderRepository.delete(orderToDelete);
        return _orderMapper.OrderToOrderDTO(orderToDelete);
    }

}
