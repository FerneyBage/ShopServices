package services.shop.Dtos.EntitiesDto.PaymentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentNotCasDTO {
    private Long id;
    private Long orderId;
    private double totalPayment;
    private LocalDateTime paymentDate;
    private String paymentMethod;
}
