package services.shop.Dtos.MapperDto;

import org.mapstruct.factory.Mappers;
import services.shop.Dtos.EntitiesDto.ProductDto;
import services.shop.entities.Product;

import java.util.List;

public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDTO(Product product);
    List<ProductDto> productsToProductDTOs(List<Product> products);
}
