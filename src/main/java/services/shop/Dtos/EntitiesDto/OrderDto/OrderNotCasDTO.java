package services.shop.Dtos.EntitiesDto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderNotCasDTO {
    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private Long statusId;
    private Long paymentId;
    private Long shippingDetailId;
}
