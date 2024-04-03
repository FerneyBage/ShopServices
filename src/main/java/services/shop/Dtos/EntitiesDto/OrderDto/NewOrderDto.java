package services.shop.Dtos.EntitiesDto.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDto {
    private Long customerId;
    private Long statusId;
}
