package services.shop.Dtos.EntitiesDto.OrderItemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;
import services.shop.entities.Order;
import services.shop.entities.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private OrderNotCasDTO order;
    private ProductDto product;
    private int quantity;
    private double unitPrice;
}
