package services.shop.Dtos.EntitiesDto.ProductsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDto {
    private String name;
    private double price;
    private int stock;
}
