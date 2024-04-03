package services.shop.services.implementation;

import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.PaymentDto.NewPaymentDto;
import services.shop.Dtos.EntitiesDto.PaymentDto.PaymentDto;
import services.shop.Dtos.MapperDto.IPaymentMapper;
import services.shop.Exceptions.OrderNotFoundException;
import services.shop.Exceptions.PaymentNotFoundException;
import services.shop.entities.Order;
import services.shop.entities.Payment;
import services.shop.repositories.IOrderRepository;
import services.shop.repositories.IPaymentRepository;
import services.shop.services.contract.IPaymentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {
    private final IPaymentRepository _paymentRepository;
    private final IOrderRepository _orderRepository;
    private final IPaymentMapper _paymentMapper;

    public PaymentService(IPaymentRepository paymentRepository, IOrderRepository orderRepository, IPaymentMapper paymentMapper) {
        _paymentRepository = paymentRepository;
        _orderRepository = orderRepository;
        _paymentMapper = paymentMapper;
    }
    public PaymentDto getPaymentById(long id){
        Payment payment = _paymentRepository.findById(id)
                         .orElseThrow(()-> new PaymentNotFoundException("Payment not found"));
        return _paymentMapper.paymentToPaymentDto(payment);
    }
    public List<PaymentDto> getALlPayment(){
        List<Payment> payments = _paymentRepository.findAll();
        return _paymentMapper.paymentsToPaymentDtos(payments);
    }
    public List<PaymentDto> getPaymentByOrderId(Long orderId){
        Order order = _orderRepository.findById(orderId)
                     .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        List<Payment> payments = _paymentRepository.findByOrderId(order.getId());
        return _paymentMapper.paymentsToPaymentDtos(payments);
    }
    public List<PaymentDto> getPaymentsByDate(LocalDateTime StartDate, LocalDateTime EndDate){
        List<Payment> payments = _paymentRepository.findByPaymentDateBetween(StartDate, EndDate);
        return _paymentMapper.paymentsToPaymentDtos(payments);
    }
    public PaymentDto addPayment(NewPaymentDto newPaymentDto) throws OrderNotFoundException{
        Order order = _orderRepository.findById(newPaymentDto.getOrderId())
                     .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        double totalPayment = 0;
        if(order.getOrderItems() != null){
            totalPayment = order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getUnitPrice()).sum();
        }
        if(order.getPayment() != null){
            Payment paymentCreatedBefore = order.getPayment();
            paymentCreatedBefore.setTotalPayment(totalPayment);
            _paymentRepository.save(paymentCreatedBefore);
            return _paymentMapper.paymentToPaymentDto(order.getPayment());
        }
        Payment paymentToCreate = _paymentMapper.newPaymentDtoToPayment(newPaymentDto);
        paymentToCreate.setOrder(order);
        paymentToCreate.setTotalPayment(totalPayment);
        Payment paymentCreated = _paymentRepository.save(paymentToCreate);
        return _paymentMapper.paymentToPaymentDto(paymentCreated);
    }
    public PaymentDto UpdatePayment(Long id, NewPaymentDto newPaymentDto){
        Payment paymentToUpdate = _paymentRepository.findById(id)
                .orElseThrow(()-> new PaymentNotFoundException("Payment not found"));
        Order order = _orderRepository.findById(newPaymentDto.getOrderId())
                .orElseThrow(()-> new OrderNotFoundException("Order not found"));
        double totalPayment = 0;
        if(order.getOrderItems() != null){
            totalPayment = order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getUnitPrice()).sum();
        }
        _paymentMapper.updatePaymentFromDto(newPaymentDto, paymentToUpdate);
        paymentToUpdate.setId(id);
        paymentToUpdate.setOrder(order);
        paymentToUpdate.setTotalPayment(totalPayment);
        Payment paymentUpdated = _paymentRepository.save(paymentToUpdate);
        return _paymentMapper.paymentToPaymentDto(paymentUpdated);
    }
    public PaymentDto deletePayment(Long id){
        Payment paymentToDelete = _paymentRepository.findById(id)
                .orElseThrow(()-> new PaymentNotFoundException("Payment not found"));
        _paymentRepository.delete(paymentToDelete);
        return _paymentMapper.paymentToPaymentDto(paymentToDelete);
    }

}
