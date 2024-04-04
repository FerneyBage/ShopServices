package services.shop.Dtos.EntitiesDto.ShiphingDto;

import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;

public class NewShiphingDetails {
    private OrderNotCasDTO order;
    private String address;
    private String carrier;
    private String trackingNumber;
}
