package services.shop.Dtos.EntitiesDto.ShiphingDto;

import jakarta.persistence.*;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;
import services.shop.entities.Order;

public class ShiphingDto {
    private Long id;
    private OrderNotCasDTO order;
    private String address;
    private String carrier;
    private String trackingNumber;
}
