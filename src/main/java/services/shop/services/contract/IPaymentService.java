package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.PaymentDto.NewPaymentDto;
import services.shop.Dtos.EntitiesDto.PaymentDto.PaymentDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IPaymentService {
    PaymentDto getPaymentById(long id);
    List<PaymentDto> getALlPayment();
    List<PaymentDto> getPaymentByOrderId(Long orderId);
    List<PaymentDto> getPaymentsByDate(LocalDateTime StartDate, LocalDateTime EndDate);
    PaymentDto addPayment(NewPaymentDto newPaymentDto);
    PaymentDto UpdatePayment(Long id, NewPaymentDto newPaymentDto);
    PaymentDto deletePayment(Long id);
}
