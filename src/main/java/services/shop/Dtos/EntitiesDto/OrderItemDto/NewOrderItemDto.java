package services.shop.Dtos.EntitiesDto.OrderItemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.shop.Dtos.EntitiesDto.OrderDto.OrderNotCasDTO;
import services.shop.Dtos.EntitiesDto.ProductsDto.ProductDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderItemDto {
    private Long orderId;
    private Long productId;
    private int quantity;
}
