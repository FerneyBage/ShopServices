package services.shop.services.implementation;

import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.OrderItemDto.NewOrderItemDto;
import services.shop.Dtos.EntitiesDto.OrderItemDto.OrderItemDto;
import services.shop.Dtos.MapperDto.IOrderItemMapper;
import services.shop.Exceptions.*;
import services.shop.entities.Order;
import services.shop.entities.OrderItem;
import services.shop.entities.Payment;
import services.shop.entities.Product;
import services.shop.repositories.IOrderItemRepository;
import services.shop.repositories.IOrderRepository;
import services.shop.repositories.IPaymentRepository;
import services.shop.repositories.IProductRepository;
import services.shop.services.contract.IOrderItemService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements IOrderItemService {
    private final IOrderItemRepository _orderItemRepository;
    private final IOrderRepository _orderRepository;
    private final IProductRepository _productRepository;
    private final IPaymentRepository _paymentRepository;

    private final IOrderItemMapper _orderItemMapper;

    public OrderItemService(IOrderItemRepository orderItemRepository, IOrderRepository orderRepository,
                            IProductRepository productRepository, IPaymentRepository paymentRepository,
                            IOrderItemMapper orderItemMapper) {
        _orderItemRepository = orderItemRepository;
        _orderRepository = orderRepository;
        _productRepository = productRepository;
        _paymentRepository = paymentRepository;
        _orderItemMapper = orderItemMapper;
    }
    public OrderItemDto getOrderItemById(long id){
        OrderItem orderItem = _orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id: " + id));
        return _orderItemMapper.toOrderItemDto(orderItem);
    }
    public List<OrderItemDto> getAllOrderItem(){
        List<OrderItem> orderItems = _orderItemRepository.findAll();
        return _orderItemMapper.toOrderItemDtos(orderItems);
    }
    public List<OrderItemDto> getOrderItemByOrderId(Long orderId){
        List<OrderItem> orderItems = _orderItemRepository.findByOrderId(orderId);
        return _orderItemMapper.toOrderItemDtos(orderItems);
    }
    public List<OrderItemDto> getOrderItemByProductId(Long productId){
        List<OrderItem> orderItems = _orderItemRepository.findByProductId(productId);
        return _orderItemMapper.toOrderItemDtos(orderItems);
    }
    public OrderItemDto addOrderItem(NewOrderItemDto newOrderItemDto) throws OrderNotFoundException, ProductNotFoundException{
        Order order = _orderRepository.findById(newOrderItemDto.getOrderId())
                        .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        Product product = _productRepository.findById(newOrderItemDto.getProductId())
                         .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        if(order.getOrderItems() != null) {
            Optional<OrderItem> foundItem = order.getOrderItems().stream()
                    .filter(orderItem -> orderItem.getProduct().getId().equals(product.getId()))
                    .findFirst();
            if (foundItem.isPresent()) {
                OrderItem orderItemCreatedBefore = foundItem.get();

                int TotalQuantity = orderItemCreatedBefore.getQuantity() + newOrderItemDto.getQuantity();
                if (TotalQuantity > product.getStock()) throw new InsufficientStockException(
                        "The requested quantity for the product" + product.getName() + "exceeds our available stock. Available quantity:" + product.getStock());

                orderItemCreatedBefore.setQuantity(TotalQuantity);
                _orderItemRepository.save(orderItemCreatedBefore);
                return _orderItemMapper.toOrderItemDto(orderItemCreatedBefore);
            }
        }
        if(product.getStock() < newOrderItemDto.getQuantity()){
            throw new InsufficientStockException(
                    "The requested quantity for the product" + product.getName() + "exceeds our available stock. Available quantity:" + product.getStock());
        }
        OrderItem orderItemToCreate = new OrderItem();
        orderItemToCreate.setUnitPrice(product.getPrice());
        orderItemToCreate.setQuantity(newOrderItemDto.getQuantity());
        orderItemToCreate.setProduct(product);
        orderItemToCreate.setOrder(order);
        OrderItem orderItemCreated = _orderItemRepository.save(orderItemToCreate);
        UpdatePayment(order.getId());
        return _orderItemMapper.toOrderItemDto(orderItemCreated);
    }
    public  OrderItemDto UpdateOrderItem(long id, NewOrderItemDto newOrderItemDto)throws OrderNotFoundException, ProductNotFoundException{
        OrderItem orderItemToUpdate = _orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id: " + id));
        Order order = _orderRepository.findById(newOrderItemDto.getOrderId())
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        Product product = _productRepository.findById(newOrderItemDto.getProductId())
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        if(product.getStock() < newOrderItemDto.getQuantity()){
            throw new InsufficientStockException(
                    "The requested quantity for the product" + product.getName() + "exceeds our available stock. Available quantity:" + product.getStock());
        }
        orderItemToUpdate.setUnitPrice(product.getPrice());
        orderItemToUpdate.setQuantity(newOrderItemDto.getQuantity());
        orderItemToUpdate.setProduct(product);
        orderItemToUpdate.setOrder(order);
        OrderItem orderItemUpdated = _orderItemRepository.save(orderItemToUpdate);
        UpdatePayment(order.getId());
        return _orderItemMapper.toOrderItemDto(orderItemUpdated);
    }
    public OrderItemDto deleteOrderItem(long id){
        OrderItem orderItemToDelete = _orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id: " + id));
        _orderItemRepository.delete(orderItemToDelete);
        UpdatePayment(orderItemToDelete.getOrder().getId());
        return _orderItemMapper.toOrderItemDto(orderItemToDelete);
    }
    private void UpdatePayment(long idOrder) throws OrderNotFoundException{
        double totalPayment = 0;
        Order order = _orderRepository.findById(idOrder)
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        if(order.getPayment() != null){
            Payment payment = order.getPayment();
            if(order.getOrderItems() != null){
                totalPayment = order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getUnitPrice()).sum();
                payment.setTotalPayment(totalPayment);
                _paymentRepository.save(payment);
            }
        }
    }
}
