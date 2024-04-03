package services.shop.Dtos.EntitiesDto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.shop.entities.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Customer customer;
    private Date orderDate;
    private Status status;
    private List<OrderItem> orderItems;
    private Payment payment;
    private ShippingDetail shippingDetail;
}
