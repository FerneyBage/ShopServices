package services.shop.Dtos.EntitiesDto.OrderDto;

import services.shop.entities.*;

import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;
    private Customer customer;
    private Date orderDate;
    private Status status;
    private List<OrderItem> orderItems;
    private Payment payment;
    private ShippingDetail shippingDetail;
}
