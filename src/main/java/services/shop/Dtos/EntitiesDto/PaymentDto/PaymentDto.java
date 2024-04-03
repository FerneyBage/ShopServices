package services.shop.Dtos.EntitiesDto.PaymentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;
import services.shop.entities.Order;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;
    private OrderNotCasDTO order;
    private double totalPayment;
    private LocalDateTime paymentDate;
    private String paymentMethod;
}
