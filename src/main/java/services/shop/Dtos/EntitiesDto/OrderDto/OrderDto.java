package services.shop.Dtos.EntitiesDto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.shop.Dtos.EntitiesDto.CustomersDto.CustomerDto;
import services.shop.Dtos.EntitiesDto.PaymentDto.PaymentNotCasDTO;
import services.shop.entities.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private CustomerDto customer;
    private LocalDateTime orderDate;
    private Status status;
    private List<OrderItem> orderItems;
    private PaymentNotCasDTO payment;
    private ShippingDetail shippingDetail;
}
