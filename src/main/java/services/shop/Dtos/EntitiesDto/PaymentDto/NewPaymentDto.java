package services.shop.Dtos.EntitiesDto.PaymentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPaymentDto {
    private Long orderId;
    private LocalDateTime paymentDate;
    private String paymentMethod;
}
